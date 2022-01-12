package com.chan.chang.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chan.chang.goods.mapper.BrandMapper;
import com.chan.chang.goods.pojo.Brand;
import com.chan.chang.goods.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAllBrand() {
        return brandMapper.selectList(null);
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectById(id);
    }

    @Override
    public void addBrand(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void updateBrand(Brand brand, Integer id) {
        // UpdateWrapper<Brand> updateWrapper = new UpdateWrapper<>();
        // updateWrapper.eq("name", brand.getName());
        // brandMapper.update(brand, updateWrapper);
        brand.setId(id);
        brandMapper.updateById(brand);
    }

    @Override
    public void deleteBrand(Integer id) {
        brandMapper.deleteById(id);
    }

    @Override
    public List<Brand> findByCondition(Brand brand) {
        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        if (brand == null) {
            return brandMapper.selectList(null);
        } else {
            if (!StringUtils.isEmpty(brand.getName())) {
                wrapper.like("name", brand.getName());
            }
            if (!StringUtils.isEmpty(brand.getImage())) {
                wrapper.like("image", brand.getImage());
            }
            if (!StringUtils.isEmpty(brand.getLetter())) {
                wrapper.like("letter", brand.getLetter());
            }
            if (brand.getId() != null && brand.getId() != 0) {
                wrapper.eq("id", brand.getId());
            }
            if (brand.getSeq() != null) {
                wrapper.eq("seq", brand.getSeq());
            }
            return brandMapper.selectList(wrapper);
        }
    }

    @Override
    public IPage<Brand> findByPage(int page, int size) {
        Page<Brand> brandPage = new Page<>(page, size);
        return brandMapper.selectPage(brandPage, null);
    }

    @Override
    public IPage<Brand> findByPageWithCondition(int page, int size, Brand brand) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        Page<Brand> brandPage = new Page<>(page, size);
        if (brand == null) {
            return brandMapper.selectPage(brandPage, null);
        } else {
            if (!StringUtils.isEmpty(brand.getName())) {
                queryWrapper.like("name", brand.getName());
            }
            if (!StringUtils.isEmpty(brand.getImage())) {
                queryWrapper.like("image", brand.getImage());
            }
            if (!StringUtils.isEmpty(brand.getLetter())) {
                queryWrapper.like("letter", brand.getLetter());
            }
            if (brand.getId() != null && brand.getId() != 0) {
                queryWrapper.eq("id", brand.getId());
            }
            if (brand.getSeq() != null) {
                queryWrapper.eq("seq", brand.getSeq());
            }
            return brandMapper.selectPage(brandPage, queryWrapper);
        }
    }
}
