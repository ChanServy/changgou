package com.chan.chang.goods.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.common.entity.Result;
import com.chan.chang.common.entity.StatusCode;
import com.chan.chang.goods.pojo.Brand;
import com.chan.chang.goods.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * 查询全部品牌数据
     *
     * @return Result<Brand>
     */
    @GetMapping
    public Result<Brand> findAllBrand() {
        List<Brand> brands = brandService.findAllBrand();
        return new Result<>(true, StatusCode.OK, "查询成功", brands);
    }

    /**
     * 根据id查询品牌
     *
     * @param id 品牌id
     * @return Brand
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable("id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", brand);
    }

    /**
     * 添加品牌
     *
     * @param brand 品牌
     * @return null
     */
    @PostMapping
    public Result<Void> addBrand(@RequestBody Brand brand) {
        brandService.addBrand(brand);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改品牌
     */
    @PutMapping("/{id}")
    public Result<Void> updateBrand(@RequestBody Brand brand, @PathVariable("id") Integer id) {
        brandService.updateBrand(brand, id);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除品牌
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteBrand(@PathVariable("id") Integer id) {
        brandService.deleteBrand(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 品牌列表条件查询
     */
    @PostMapping("/search")
    public Result<Brand> findBrandByCondition(@RequestBody(required = false) Brand brand) {
        List<Brand> brands = brandService.findByCondition(brand);
        return new Result<>(true, StatusCode.OK, "查询成功", brands);
    }

    /**
     * 分页查询
     */
    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Brand>> findBrandByPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        IPage<Brand> brandIPage = brandService.findByPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", brandIPage);
    }

    /**
     * 分页查询(带条件)
     */
    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Brand>> findBrandByPageWithCondition(
            @PathVariable("page") int page,
            @PathVariable("size") int size,
            @RequestBody(required = false) Brand brand
    ) {
        IPage<Brand> brandIPage = brandService.findByPageWithCondition(page, size, brand);
        return new Result<>(true, StatusCode.OK, "查询成功", brandIPage);
    }
}
