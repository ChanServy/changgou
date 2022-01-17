package com.chan.chang.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.goods.pojo.Template;

import java.util.List;

public interface TemplateService {
    IPage<Template> findPageByCondition(Template template, int page, int size);

    IPage<Template> findTemplatePage(int page, int size);

    List<Template> findByCondition(Template template);

    void delete(Integer id);

    void update(Integer id, Template template);

    void add(Template template);

    List<Template> findAll();

    Template findById(Integer id);

    Template findByCategory(Integer id);
}
