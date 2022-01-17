package com.chan.chang.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chan.chang.goods.mapper.BrandMapper;
import com.chan.chang.goods.mapper.CategoryMapper;
import com.chan.chang.goods.mapper.SkuMapper;
import com.chan.chang.goods.mapper.SpuMapper;
import com.chan.chang.goods.pojo.*;
import com.chan.chang.goods.service.SpuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SpuServiceImpl implements SpuService {
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    SpuMapper spuMapper;
    @Autowired
    SkuMapper skuMapper;

    @Override
    public void saveGoods(Goods goods) {
        //插入spu
        Spu spu = goods.getSpu();
        if (spu.getId() == null) {
            //新增
            spuMapper.insert(spu);
        } else {
            //修改
            spuMapper.updateById(spu);
            //删除该spu的sku
            skuMapper.delete(new Wrapper<Sku>() {
                @Override
                public Sku getEntity() {
                    Sku sku = new Sku();
                    sku.setSpuId(spu.getId());
                    return sku;
                }

                @Override
                public MergeSegments getExpression() {
                    return null;
                }

                @Override
                public String getSqlSegment() {
                    return null;
                }
            });
        }
        //插入sku
        Category category = categoryMapper.selectById(spu.getCategory3Id());
        Brand brand = brandMapper.selectById(spu.getBrandId());
        List<Sku> skuList = goods.getSkuList();
        for (Sku sku : skuList) {
            //构建SKU名称，采用SPU+规格值组装
            if (StringUtils.isEmpty(sku.getSpec())) {
                sku.setSpec("{}");
            }
            //获取Spu的名字
            String spuName = spu.getName();
            //将规格列表转换成Map
            Map<String, String> specMap = JSON.parseObject(sku.getSpec(), Map.class);
            //循环组装Sku的名字
            for (String key : specMap.keySet()) {
                spuName = spuName + " " + specMap.get(key);
            }
            //sku的id利用了mp的ID_WORKER策略
            //sku的创建时间和修改时间利用了mp的自动填充
            sku.setName(spuName);//此时的spuName为spu的name加上spec的值
            sku.setSpuId(spu.getId());
            sku.setCategoryId(spu.getCategory3Id());
            sku.setCategoryName(category.getName());
            sku.setBrandName(brand.getName());
            skuMapper.insert(sku);
        }
    }

    @Override
    public Goods findById(Long id) {
        Spu spu = spuMapper.selectById(id);
        QueryWrapper<Sku> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id", id);
        List<Sku> skuList = skuMapper.selectList(wrapper);
        Goods goods = new Goods();
        goods.setSpu(spu);
        goods.setSkuList(skuList);
        return goods;
    }

    @Override
    public void audit(Long id) {
        Spu spu = spuMapper.selectById(id);
        if (spu.getIsDelete().equals("1")) {
            throw new RuntimeException("该商品已经删除!");
        } else {
            spu.setStatus("1");//审核通过
            spu.setIsMarketable("1");//上架
            spuMapper.updateById(spu);
        }
    }

    @Override
    public void pull(Long id) {
        Spu spu = spuMapper.selectById(id);
        if (spu.getIsDelete().equals("1")) {
            throw new RuntimeException("此商品已删除！");
        } else {
            spu.setIsMarketable("0");//下架状态
            spuMapper.updateById(spu);
        }
    }

    @Override
    public void put(Long id) {
        Spu spu = spuMapper.selectById(id);
        if (spu.getIsDelete().equals("1")) {
            throw new RuntimeException("此商品已删除！");
        }
        if (!spu.getStatus().equals("1")) {
            throw new RuntimeException("未通过审核的商品不能上架！");
        }
        spu.setIsMarketable("1");//上架状态
        spuMapper.updateById(spu);
    }

    @Override
    public int putMany(Long[] ids) {
        Spu spu = new Spu();
        spu.setIsMarketable("1");//上架
        UpdateWrapper<Spu> wrapper = new UpdateWrapper<>();
        //批量上架未上架的、审核通过的、未删除的
        wrapper.in("id", Arrays.asList(ids)).eq("is_marketable", "0").eq("status", "1").eq("is_delete", "0");
        return spuMapper.update(spu, wrapper);
    }

    @Override
    public int pullMany(Long[] ids) {
        Spu spu = new Spu();
        spu.setIsMarketable("0");//下架
        QueryWrapper<Spu> wrapper = new QueryWrapper<>();
        wrapper.in("id", Arrays.asList(ids)).eq("is_marketable", "1").eq("status", "1").eq("is_delete", "0");
        return spuMapper.update(spu, wrapper);
    }

    @Override
    public void logicDelete(Long id) {
        Spu spu = spuMapper.selectById(id);
        if (!spu.getIsMarketable().equals("0")) {
            //上架的商品不能直接删除，需要先下架再删除
            throw new RuntimeException("必须先下架再删除");
        }
        //删除状态
        spu.setIsDelete("1");
        //未审核
        spu.setStatus("0");
        spuMapper.updateById(spu);
    }

    @Override
    public void restore(Long id) {
        Spu spu = spuMapper.selectById(id);
        if (!spu.getIsDelete().equals("1")) {
            throw new RuntimeException("此商品未删除！");
        }
        spu.setIsDelete("0");
        spu.setStatus("0");
        spuMapper.updateById(spu);
    }

    @Override
    public void delete(Long id) {
        Spu spu = spuMapper.selectById(id);
        //先检查商品是否被逻辑删除了，逻辑删除的商品才能删除
        if (!spu.getIsDelete().equals("1")) {
            throw new RuntimeException("此商品不能删除！");
        }
        spuMapper.deleteById(id);
    }

    @Override
    public IPage<Spu> findPage(Spu spu, int page, int size) {
        Page<Spu> spuPage = new Page<>(page, size);
        if (spu == null) {
            return spuMapper.selectPage(spuPage, null);
        }
        Wrapper<Spu> wrapper = createWrapper(spu);
        return spuMapper.selectPage(spuPage, wrapper);
    }

    public Wrapper<Spu> createWrapper(Spu spu) {
        QueryWrapper<Spu> wrapper = new QueryWrapper<>();
        if (spu != null) {
            if (spu.getId() != null && spu.getId() != 0) {
                wrapper.eq("id", spu.getId());
            }
            if (!StringUtils.isEmpty(spu.getSn())) {
                wrapper.eq("sn", spu.getSn());
            }
            if (!StringUtils.isEmpty(spu.getName())) {
                wrapper.like("name", spu.getName());
            }
            if (!StringUtils.isEmpty(spu.getCaption())) {
                wrapper.like("caption", spu.getCaption());
            }
            if (spu.getBrandId() != null && spu.getBrandId() != 0) {
                wrapper.eq("brand_id", spu.getBrandId());
            }
            if (spu.getCategory1Id() != null && spu.getCategory1Id() != 0) {
                wrapper.eq("category1_id", spu.getCategory1Id());
            }
            if (spu.getCategory2Id() != null && spu.getCategory2Id() != 0) {
                wrapper.eq("category2_id", spu.getCategory2Id());
            }
            if (spu.getCategory3Id() != null && spu.getCategory3Id() != 0) {
                wrapper.eq("category3_id", spu.getCategory3Id());
            }
            if (spu.getTemplateId() != null && spu.getTemplateId() != 0) {
                wrapper.eq("template_id", spu.getTemplateId());
            }
            if (spu.getFreightId() != null && spu.getFreightId() != 0) {
                wrapper.eq("freight_id", spu.getFreightId());
            }
            if (!StringUtils.isEmpty(spu.getImage())) {
                wrapper.eq("image", spu.getImage());
            }
            if (!StringUtils.isEmpty(spu.getIntroduction())) {
                wrapper.like("introduction", spu.getIntroduction());
            }
            if (spu.getSaleNum() != null) {
                wrapper.eq("sale_num", spu.getSaleNum());
            }
            if (!StringUtils.isEmpty(spu.getIsMarketable())) {
                wrapper.eq("is_marketable", spu.getIsMarketable());
            }
            if (!StringUtils.isEmpty(spu.getIsDelete())) {
                wrapper.eq("is_delete", spu.getIsDelete());
            }
            if (!StringUtils.isEmpty(spu.getStatus())) {
                wrapper.eq("status", spu.getStatus());
            }
        }
        return wrapper;
    }
}
