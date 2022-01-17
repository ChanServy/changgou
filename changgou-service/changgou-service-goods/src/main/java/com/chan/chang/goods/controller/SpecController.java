package com.chan.chang.goods.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.common.entity.Result;
import com.chan.chang.common.entity.StatusCode;
import com.chan.chang.goods.pojo.Spec;
import com.chan.chang.goods.service.SpecService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController {
    private final SpecService specService;

    public SpecController(SpecService specService) {
        this.specService = specService;
    }

    /**
     * 按条件分页查询
     */
    @PostMapping("search/{page}/{size}")
    public Result<IPage<Spec>> findSpecPageByCondition(
            @RequestBody(required = false) Spec spec,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        IPage<Spec> specIPage = specService.findPageByCondition(spec,page,size);
        return new Result<>(true, StatusCode.OK,"查询成功",specIPage);
    }

    /**
     * 分页查询所有
     */
    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Spec>> findSpecPage(@PathVariable("page")int page,@PathVariable("size")int size){
        IPage<Spec> specIPage = specService.findSpecPage(page,size);
        return new Result<>(true,StatusCode.OK,"success",specIPage);
    }

    /**
     * 按条件查询
     */
    @PostMapping("/search")
    public Result<List<Spec>> findSpecByCondition(@RequestBody(required = false) Spec spec){
        List<Spec> specs = specService.findByCondition(spec);
        return new Result<>(true,StatusCode.OK,"success",specs);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteSpec(@PathVariable("id")Integer id){
        specService.delete(id);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 修改，id必须，前端json对象中的属性传值就改，属性不写就不改，mp内部判断，简化动态sql
     */
    @PutMapping("/{id}")
    public Result<Void> updateSpec(@PathVariable("id")Integer id, @RequestBody Spec spec){
        specService.update(id, spec);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 添加 id不需，根据策略自动
     */
    @PostMapping
    public Result<Void> addSpec(@RequestBody Spec spec){
        specService.add(spec);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 查询所有
     */
    @GetMapping
    public Result<List<Spec>> findAllSpec(){
        List<Spec> specs = specService.findAll();
        return new Result<>(true,StatusCode.OK,"success",specs);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable("id")Integer id){
        Spec spec = specService.findById(id);
        return new Result<>(true,StatusCode.OK,"success",spec);
    }

    /**
     * 根据分类ID查询规格列表
     */
    @GetMapping("/category/{id}")
    public Result<List<Spec>> findByCategory(@PathVariable("id")Integer id){
        List<Spec> specs = specService.findByCategory(id);
        return new Result<>(true,StatusCode.OK,"success",specs);
    }
}
