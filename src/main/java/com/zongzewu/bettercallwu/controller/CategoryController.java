package com.zongzewu.bettercallwu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zongzewu.bettercallwu.common.R;
import com.zongzewu.bettercallwu.entity.Category;
import com.zongzewu.bettercallwu.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * new category
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category: {}", category);
        categoryService.save(category);
        return R.success("successfully added new category");
    }

    /**
     * paging query
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //conditional constructor
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //add sorting method, ascending
        queryWrapper.orderByAsc(Category::getSort);
        // do paging query
        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }
@DeleteMapping
    public R<String> delete(Long id){
        log.info("delete category, whose id is: {}", id);

        //categoryService.removeById(id);
    categoryService.remove(id);

    return R.success("category info deleted successfully");
    }

    /**
     * modify category information by id
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("Modify category info: {}", category);
        categoryService.updateById(category);
        return R.success("Successfully modified this category");
    }
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        //condition constructor
        LambdaQueryWrapper<Category>  queryWrapper = new LambdaQueryWrapper<>();
        //add condition
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        //add sorting method
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}
