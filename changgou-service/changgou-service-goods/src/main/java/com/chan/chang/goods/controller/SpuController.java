package com.chan.chang.goods.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.common.entity.Result;
import com.chan.chang.common.entity.StatusCode;
import com.chan.chang.goods.pojo.Goods;
import com.chan.chang.goods.pojo.Spu;
import com.chan.chang.goods.service.SpuService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spu")
public class SpuController {

    final
    SpuService spuService;

    public SpuController(SpuService spuService) {
        this.spuService = spuService;
    }

    @PostMapping("/save")
    public Result<Void> save(@RequestBody Goods goods){
        spuService.saveGoods(goods);
        return new Result<>(true, StatusCode.OK,"success");
    }

    @GetMapping("/goods/{id}")
    public Result<Goods> findGoodsById(@PathVariable("id") Long id){
        Goods goods = spuService.findById(id);
        return new Result<>(true,StatusCode.OK,"success",goods);
    }

    /**
     * 审核
     */
    @PutMapping("/audit/{id}")
    public Result<Void> audit(@PathVariable("id") Long id){
        spuService.audit(id);
        return new Result<>(true,StatusCode.OK,"审核成功");
    }

    /**
     * 下架
     */
    @PutMapping("/pull/{id}")
    public Result<Void> pull(@PathVariable("id")Long id){
        spuService.pull(id);
        return new Result<>(true,StatusCode.OK,"下架成功");
    }

    /**
     * 商品上架
     */
    @PutMapping("/put/{id}")
    public Result<Void> put(@PathVariable("id")Long id){
        spuService.put(id);
        return new Result<>(true,StatusCode.OK,"上架成功");
    }

    /**
     * 批量上架
     */
    @PutMapping("/put/many")
    public Result<Void> putMany(@RequestBody Long[] ids){
        int count = spuService.putMany(ids);
        return new Result<>(true,StatusCode.OK,"上架"+count+"个商品");
    }

    /**
     * 批量下架
     */
    @PutMapping("/pull/many")
    public Result<Void> pullMany(@RequestBody Long[] ids){
        int count = spuService.pullMany(ids);
        return new Result<>(true,StatusCode.OK,"下架"+count+"个商品");
    }

    /**
     * 逻辑删除
     */
    @PutMapping("/logic/delete/{id}")
    public Result<Void> logicDelete(@PathVariable("id")Long id){
        spuService.logicDelete(id);
        return new Result<>(true,StatusCode.OK,"逻辑删除成功");
    }

    /**
     * 恢复数据
     */
    @PutMapping("/restore/{id}")
    public Result<Void> restore(@PathVariable("id") Long id){
        spuService.restore(id);
        return new Result<>(true,StatusCode.OK,"数据恢复成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable("id")Long id){
        spuService.delete(id);
        return new Result<>(true,StatusCode.OK,"删除成功");
    }

    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Spu>> findPage(@RequestBody(required = false) Spu spu, @PathVariable int page,@PathVariable int size){
        IPage<Spu> spuIPage = spuService.findPage(spu,page,size);
        return new Result<>(true,StatusCode.OK,"success",spuIPage);
    }
}
