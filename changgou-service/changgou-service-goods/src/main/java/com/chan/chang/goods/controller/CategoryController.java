package com.chan.chang.goods.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.common.entity.Result;
import com.chan.chang.common.entity.StatusCode;
import com.chan.chang.goods.pojo.Category;
import com.chan.chang.goods.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 按条件分页查询
     */
    @PostMapping("search/{page}/{size}")
    public Result<IPage<Category>> findCategoryPageByCondition(
            @RequestBody(required = false) Category category,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    ) {
        IPage<Category> categoryIPage = categoryService.findPageByCondition(category,page,size);
        return new Result<>(true, StatusCode.OK,"查询成功",categoryIPage);
    }

    /**
     * 分页查询所有
     */
    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Category>> findCategoryPage(@PathVariable("page")int page,@PathVariable("size")int size){
        IPage<Category> categoryIPage = categoryService.findCategoryPage(page,size);
        return new Result<>(true,StatusCode.OK,"success",categoryIPage);
    }

    /**
     * 按条件查询
     */
    @PostMapping("/search")
    public Result<List<Category>> findCategoryByCondition(@RequestBody(required = false) Category category){
        List<Category> categorys = categoryService.findByCondition(category);
        return new Result<>(true,StatusCode.OK,"success",categorys);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteCategory(@PathVariable("id")Integer id){
        categoryService.delete(id);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 修改，id必须，前端json对象中的属性传值就改，属性不写就不改，mp内部判断，简化动态sql
     */
    @PutMapping("/{id}")
    public Result<Void> updateCategory(@PathVariable("id")Integer id, @RequestBody Category category){
        categoryService.update(id, category);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 添加 id不需，根据策略自动
     */
    @PostMapping
    public Result<Void> addCategory(@RequestBody Category category){
        categoryService.add(category);
        return new Result<>(true,StatusCode.OK,"success");
    }

    /**
     * 查询所有
     */
    @GetMapping
    public Result<List<Category>> findAllCategory(){
        List<Category> categorys = categoryService.findAll();
        return new Result<>(true,StatusCode.OK,"success",categorys);
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable("id")Integer id){
        Category category = categoryService.findById(id);
        return new Result<>(true,StatusCode.OK,"success",category);
    }


    @GetMapping("/list/{pid}")
    public Result<List<Category>> findByParentId(@PathVariable("pid")Integer pid) {
        List<Category> categoryList = categoryService.findByParentId(pid);
        return new Result<>(true,StatusCode.OK,"success",categoryList);
    }

}
