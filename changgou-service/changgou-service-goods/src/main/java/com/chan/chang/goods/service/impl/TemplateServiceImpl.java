package com.chan.chang.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chan.chang.goods.mapper.CategoryMapper;
import com.chan.chang.goods.mapper.TemplateMapper;
import com.chan.chang.goods.pojo.Category;
import com.chan.chang.goods.pojo.Template;
import com.chan.chang.goods.service.TemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    TemplateMapper templateMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public IPage<Template> findPageByCondition(Template template, int page, int size) {
        Page<Template> templatePage = new Page<>(page,size);
        if (template == null){
            return templateMapper.selectPage(templatePage, null);
        }else {
            Wrapper<Template> wrapper = createWrapper(template);
            return templateMapper.selectPage(templatePage, wrapper);
        }
    }

    @Override
    public IPage<Template> findTemplatePage(int page, int size) {
        Page<Template> templatePage = new Page<>(page,size);
        return templateMapper.selectPage(templatePage,null);
    }

    @Override
    public List<Template> findByCondition(Template template) {
        Wrapper<Template> wrapper = createWrapper(template);
        return templateMapper.selectList(wrapper);
    }

    @Override
    public void delete(Integer id) {
        templateMapper.deleteById(id);
    }

    @Override
    public void update(Integer id, Template template) {
        template.setId(id);
        templateMapper.updateById(template);
    }

    @Override
    public void add(Template template) {
        templateMapper.insert(template);
    }

    @Override
    public List<Template> findAll() {
        return templateMapper.selectList(null);
    }

    @Override
    public Template findById(Integer id) {
        return templateMapper.selectById(id);
    }

    @Override
    public Template findByCategory(Integer id) {
        Category category = categoryMapper.selectById(id);
        Integer templateId = category.getTemplateId();
        return templateMapper.selectById(templateId);
    }

    public Wrapper<Template> createWrapper(Template template){
        QueryWrapper<Template> wrapper = new QueryWrapper<>();
        if (template!=null){
            if (template.getId()!=null&&template.getId()!=0){
                wrapper.eq("id",template.getId());
            }
            if (!StringUtils.isEmpty(template.getName())){
                wrapper.like("name",template.getName());
            }
            if (template.getSpecNum()!=null){
                wrapper.eq("spec_num",template.getSpecNum());
            }
            if (template.getParaNum()!=null){
                wrapper.eq("para_num",template.getParaNum());
            }
        }
        return wrapper;
    }
}
