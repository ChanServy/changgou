package com.chan.chang.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chan.chang.goods.mapper.CategoryMapper;
import com.chan.chang.goods.pojo.Category;
import com.chan.chang.goods.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public IPage<Category> findPageByCondition(Category category, int page, int size) {
        Page<Category> categoryPage = new Page<>(page,size);
        if (category==null){
            return categoryMapper.selectPage(categoryPage,null);
        }else {
            Wrapper<Category> wrapper = createWrapper(category);
            return categoryMapper.selectPage(categoryPage, wrapper);
        }
    }

    @Override
    public IPage<Category> findCategoryPage(int page, int size) {
        Page<Category> categoryPage = new Page<>(page,size);
        return categoryMapper.selectPage(categoryPage,null);
    }

    @Override
    public List<Category> findByCondition(Category category) {
        Wrapper<Category> wrapper = createWrapper(category);
        return categoryMapper.selectList(wrapper);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.deleteById(id);
    }

    @Override
    public void update(Integer id, Category category) {
        category.setId(id);
        categoryMapper.updateById(category);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectList(null);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public List<Category> findByParentId(Integer pid) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",pid);
        return categoryMapper.selectList(wrapper);
    }

    public Wrapper<Category> createWrapper(Category category){
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (category!=null){
            //分类id
            if (category.getId()!=null&&category.getId()!=0){
                wrapper.eq("id",category.getId());
            }
            //分类名称
            if (!StringUtils.isEmpty(category.getName())){
                wrapper.like("name",category.getName());
            }
            //商品数量
            if (category.getGoodsNum()!=null){
                wrapper.eq("goods_num",category.getGoodsNum());
            }
            //是否显示
            if (!StringUtils.isEmpty(category.getIsShow())){
                wrapper.eq("is_show",category.getIsShow());
            }
            //是否导航
            if (!StringUtils.isEmpty(category.getIsMenu())){
                wrapper.eq("is_menu",category.getIsMenu());
            }
            //排序
            if (category.getSeq()!=null){
                wrapper.eq("seq",category.getSeq());
            }
            //上级ID
            if (category.getParentId()!=null){
                wrapper.eq("parent_id",category.getParentId());
            }
            //模板id
            if (category.getTemplateId()!=null){
                wrapper.eq("template_id",category.getTemplateId());
            }
        }
        return wrapper;
    }
}
