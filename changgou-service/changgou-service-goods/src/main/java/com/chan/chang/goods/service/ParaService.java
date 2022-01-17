package com.chan.chang.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.goods.pojo.Para;

import java.util.List;

public interface ParaService {
    IPage<Para> findPageByCondition(Para para, int page, int size);

    IPage<Para> findParaPage(int page, int size);

    List<Para> findByCondition(Para para);

    void delete(Integer id);

    void update(Integer id, Para para);

    void add(Para para);

    List<Para> findAll();

    Para findById(Integer id);

    List<Para> findByCategory(Integer id);
}
