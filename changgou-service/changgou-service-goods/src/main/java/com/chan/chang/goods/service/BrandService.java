package com.chan.chang.goods.service;

import com.chan.chang.goods.pojo.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAllBrand();

    Brand findById(Integer id);
}
