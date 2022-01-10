package com.chan.chang.goods.service.impl;

import com.chan.chang.goods.mapper.BrandMapper;
import com.chan.chang.goods.pojo.Brand;
import com.chan.chang.goods.service.BrandService;
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
}
