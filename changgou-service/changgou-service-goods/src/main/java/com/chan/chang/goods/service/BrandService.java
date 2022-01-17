package com.chan.chang.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.goods.pojo.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAllBrand();

    Brand findById(Integer id);

    void addBrand(Brand brand);

    void updateBrand(Brand brand, Integer id);

    void deleteBrand(Integer id);

    List<Brand> findByCondition(Brand brand);

    IPage<Brand> findByPage(int page, int size);

    IPage<Brand> findByPageWithCondition(int page, int size, Brand brand);

    List<Brand> findByCategory(Integer id);
}
