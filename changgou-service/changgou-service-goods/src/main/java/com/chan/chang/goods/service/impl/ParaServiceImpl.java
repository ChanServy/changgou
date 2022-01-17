package com.chan.chang.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chan.chang.goods.mapper.CategoryMapper;
import com.chan.chang.goods.mapper.ParaMapper;
import com.chan.chang.goods.pojo.Category;
import com.chan.chang.goods.pojo.Para;
import com.chan.chang.goods.service.ParaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParaServiceImpl implements ParaService {
    @Autowired
    ParaMapper paraMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public IPage<Para> findPageByCondition(Para para, int page, int size) {
        Page<Para> paraPage = new Page<>(page,size);
        if (para==null){
            return paraMapper.selectPage(paraPage,null);
        }else {
            Wrapper<Para> wrapper = createWrapper(para);
            return paraMapper.selectPage(paraPage,wrapper);
        }
    }

    @Override
    public IPage<Para> findParaPage(int page, int size) {
        Page<Para> paraPage = new Page<>(page,size);
        return paraMapper.selectPage(paraPage, null);
    }

    @Override
    public List<Para> findByCondition(Para para) {
        Wrapper<Para> wrapper = createWrapper(para);
        return paraMapper.selectList(wrapper);
    }

    @Override
    public void delete(Integer id) {
        paraMapper.deleteById(id);
    }

    @Override
    public void update(Integer id, Para para) {
        para.setId(id);
        paraMapper.updateById(para);
    }

    @Override
    public void add(Para para) {
        paraMapper.insert(para);
    }

    @Override
    public List<Para> findAll() {
        return paraMapper.selectList(null);
    }

    @Override
    public Para findById(Integer id) {
        return paraMapper.selectById(id);
    }

    @Override
    public List<Para> findByCategory(Integer id) {
        Category category = categoryMapper.selectById(id);
        Integer templateId = category.getTemplateId();
        QueryWrapper<Para> wrapper = new QueryWrapper<>();
        wrapper.eq("template_id",templateId);
        return paraMapper.selectList(wrapper);
    }

    public Wrapper<Para> createWrapper(Para para){
        QueryWrapper<Para> wrapper = new QueryWrapper<>();
        if (para!=null){
            if (para.getId()!=null&&para.getId()!=0){
                wrapper.eq("id",para.getId());
            }
            if (!StringUtils.isEmpty(para.getName())){
                wrapper.like("name",para.getName());
            }
            if (!StringUtils.isEmpty(para.getOptions())){
                wrapper.eq("options",para.getOptions());
            }
            if (para.getSeq()!=null){
                wrapper.eq("seq",para.getSeq());
            }
            if (para.getTemplateId()!=null){
                wrapper.eq("template_id",para.getTemplateId());
            }
        }
        return wrapper;
    }
}
