package com.chan.chang.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.goods.pojo.Goods;
import com.chan.chang.goods.pojo.Spu;

public interface SpuService {
    void saveGoods(Goods goods);

    Goods findById(Long id);

    void audit(Long id);

    void pull(Long id);

    void put(Long id);

    int putMany(Long[] ids);

    int pullMany(Long[] ids);

    void logicDelete(Long id);

    void restore(Long id);

    void delete(Long id);

    IPage<Spu> findPage(Spu spu, int page, int size);

}
