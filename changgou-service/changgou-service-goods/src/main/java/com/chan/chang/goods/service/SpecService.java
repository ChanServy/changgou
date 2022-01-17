package com.chan.chang.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.goods.pojo.Spec;

import java.util.List;

public interface SpecService {
    IPage<Spec> findPageByCondition(Spec spec, int page, int size);

    IPage<Spec> findSpecPage(int page, int size);

    List<Spec> findByCondition(Spec spec);

    void delete(Integer id);

    void update(Integer id, Spec spec);

    void add(Spec spec);

    List<Spec> findAll();

    Spec findById(Integer id);

    List<Spec> findByCategory(Integer id);
}
