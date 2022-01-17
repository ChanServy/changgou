package com.chan.chang.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chan.chang.goods.mapper.CategoryMapper;
import com.chan.chang.goods.mapper.SpecMapper;
import com.chan.chang.goods.pojo.Category;
import com.chan.chang.goods.pojo.Spec;
import com.chan.chang.goods.service.SpecService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
    SpecMapper specMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public IPage<Spec> findPageByCondition(Spec spec, int page, int size) {
        Page<Spec> specPage = new Page<>(page,size);
        if (spec==null){
            return specMapper.selectPage(specPage,null);
        }else {
            Wrapper<Spec> wrapper = createWrapper(spec);
            return specMapper.selectPage(specPage,wrapper);
        }
    }

    @Override
    public IPage<Spec> findSpecPage(int page, int size) {
        Page<Spec> specPage = new Page<>(page,size);
        return specMapper.selectPage(specPage,null);
    }

    @Override
    public List<Spec> findByCondition(Spec spec) {
        Wrapper<Spec> wrapper = createWrapper(spec);
        return specMapper.selectList(wrapper);
    }

    @Override
    public void delete(Integer id) {
        specMapper.deleteById(id);
    }

    @Override
    public void update(Integer id, Spec spec) {
        spec.setId(id);
        specMapper.updateById(spec);
    }

    @Override
    public void add(Spec spec) {
        specMapper.insert(spec);
    }

    @Override
    public List<Spec> findAll() {
        return specMapper.selectList(null);
    }

    @Override
    public Spec findById(Integer id) {
        return specMapper.selectById(id);
    }

    @Override
    public List<Spec> findByCategory(Integer id) {
        Category category = categoryMapper.selectById(id);
        Integer templateId = category.getTemplateId();
        QueryWrapper<Spec> wrapper = new QueryWrapper<>();
        wrapper.eq("template_id",templateId);
        return specMapper.selectList(wrapper);
    }

    public Wrapper<Spec> createWrapper(Spec spec){
        QueryWrapper<Spec> wrapper = new QueryWrapper<>();
        if (spec!=null){
            if (spec.getId()!=null&&spec.getId()!=0){
                wrapper.eq("id",spec.getId());
            }
            if (!StringUtils.isEmpty(spec.getName())){
                wrapper.like("name",spec.getName());
            }
            if (!StringUtils.isEmpty(spec.getOptions())){
                wrapper.eq("options",spec.getOptions());
            }
            if (spec.getSeq()!=null){
                wrapper.eq("seq",spec.getSeq());
            }
            if (spec.getTemplateId()!=null){
                wrapper.eq("template_id",spec.getTemplateId());
            }
        }
        return wrapper;
    }
}
