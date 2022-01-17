package com.chan.chang.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.goods.pojo.Category;

import java.util.List;

public interface CategoryService {
    IPage<Category> findPageByCondition(Category category, int page, int size);

    IPage<Category> findCategoryPage(int page, int size);

    List<Category> findByCondition(Category category);

    void delete(Integer id);

    void update(Integer id, Category category);

    void add(Category category);

    List<Category> findAll();

    Category findById(Integer id);

    List<Category> findByParentId(Integer pid);
}
