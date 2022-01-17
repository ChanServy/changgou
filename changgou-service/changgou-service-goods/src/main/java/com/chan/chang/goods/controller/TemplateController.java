package com.chan.chang.goods.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.common.entity.Result;
import com.chan.chang.common.entity.StatusCode;
import com.chan.chang.goods.pojo.Template;
import com.chan.chang.goods.service.TemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/template")
@CrossOrigin
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * 按条件分页查询
     */
    @PostMapping("search/{page}/{size}")
    public Result<IPage<Template>> findTemplatePageByCondition(
            @RequestBody(required = false) Template template,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        IPage<Template> templateIPage = templateService.findPageByCondition(template,page,size);
        return new Result<>(true, StatusCode.OK,"查询成功",templateIPage);
    }

    /**
     * 分页查询所有
     */
    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Template>> findTemplatePage(@PathVariable("page")int page,@PathVariable("size")int size){
        IPage<Template> templateIPage = templateService.findTemplatePage(page,size);
        return new Result<>(true,StatusCode.OK,"success",templateIPage);
    }

    /**
     * 按条件查询
     */
    @PostMapping("/search")
    public Result<List<Template>> findTemplateByCondition(@RequestBody(required = false) Template template){
        List<Template> templates = templateService.findByCondition(template);
        return new Result<>(true,StatusCode.OK,"success",templates);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteTemplate(@PathVariable("id")Integer id){
        templateService.delete(id);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 修改，id必须，前端json对象中的属性传值就改，属性不写就不改，mp内部判断，简化动态sql
     */
    @PutMapping("/{id}")
    public Result<Void> updateTemplate(@PathVariable("id")Integer id, @RequestBody Template template){
        templateService.update(id, template);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 添加 id不需，根据策略自动
     */
    @PostMapping
    public Result<Void> addTemplate(@RequestBody Template template){
        templateService.add(template);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 查询所有
     */
    @GetMapping
    public Result<List<Template>> findAllTemplate(){
        List<Template> templates = templateService.findAll();
        return new Result<>(true,StatusCode.OK,"success",templates);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Template> findById(@PathVariable("id")Integer id){
        Template template = templateService.findById(id);
        return new Result<>(true,StatusCode.OK,"success",template);
    }

    @GetMapping("/category/{id}")
    public Result<Template> findByCategoryId(@PathVariable("id")Integer id){
        Template template = templateService.findByCategory(id);
        return new Result<>(true,StatusCode.OK,"success",template);
    }
}
