package com.chan.chang.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chan.chang.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandMapper extends BaseMapper<Brand> {
    // @Select("select tb.* from tb_category_brand tcb, tb_brand tb where tcb.brand_id = tb.id and tcb.category_id = #{id}")//两种写法
    @Select("select tb.* from tb_brand tb left join tb_category_brand tcb on tb.id = tcb.brand_id where tcb.category_id = #{id}")
    List<Brand> findByCategory(Integer id);
}
