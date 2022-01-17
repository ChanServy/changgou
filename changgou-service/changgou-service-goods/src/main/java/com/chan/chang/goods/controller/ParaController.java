package com.chan.chang.goods.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.common.entity.Result;
import com.chan.chang.common.entity.StatusCode;
import com.chan.chang.goods.pojo.Para;
import com.chan.chang.goods.service.ParaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController {
    private final ParaService paraService;

    public ParaController(ParaService paraService) {
        this.paraService = paraService;
    }

    /**
     * 按条件分页查询
     */
    @PostMapping("search/{page}/{size}")
    public Result<IPage<Para>> findParaPageByCondition(
            @RequestBody(required = false) Para para,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        IPage<Para> paraIPage = paraService.findPageByCondition(para,page,size);
        return new Result<>(true, StatusCode.OK,"查询成功",paraIPage);
    }

    /**
     * 分页查询所有
     */
    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Para>> findParaPage(@PathVariable("page")int page,@PathVariable("size")int size){
        IPage<Para> paraIPage = paraService.findParaPage(page,size);
        return new Result<>(true,StatusCode.OK,"success",paraIPage);
    }

    /**
     * 按条件查询
     */
    @PostMapping("/search")
    public Result<List<Para>> findParaByCondition(@RequestBody(required = false) Para para){
        List<Para> paras = paraService.findByCondition(para);
        return new Result<>(true,StatusCode.OK,"success",paras);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public Result<Void> deletePara(@PathVariable("id")Integer id){
        paraService.delete(id);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 修改，id必须，前端json对象中的属性传值就改，属性不写就不改，mp内部判断，简化动态sql
     */
    @PutMapping("/{id}")
    public Result<Void> updatePara(@PathVariable("id")Integer id, @RequestBody Para para){
        paraService.update(id, para);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 添加 id不需，根据策略自动
     */
    @PostMapping
    public Result<Void> addPara(@RequestBody Para para){
        paraService.add(para);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 查询所有
     */
    @GetMapping
    public Result<List<Para>> findAllPara(){
        List<Para> paras = paraService.findAll();
        return new Result<>(true,StatusCode.OK,"success",paras);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Para> findById(@PathVariable("id")Integer id){
        Para para = paraService.findById(id);
        return new Result<>(true,StatusCode.OK,"success",para);
    }


    /**
     * 根据分类ID查询参数列表
     */
    @GetMapping("/category/{id}")
    public Result<List<Para>> findByCategory(@PathVariable("id")Integer id){
        List<Para> paras = paraService.findByCategory(id);
        return new Result<>(true,StatusCode.OK,"success",paras);
    }
}
