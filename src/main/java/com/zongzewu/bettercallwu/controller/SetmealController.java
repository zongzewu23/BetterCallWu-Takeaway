package com.zongzewu.bettercallwu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zongzewu.bettercallwu.common.R;
import com.zongzewu.bettercallwu.dto.SetmealDto;
import com.zongzewu.bettercallwu.entity.Category;
import com.zongzewu.bettercallwu.entity.Setmeal;
import com.zongzewu.bettercallwu.service.CategoryService;
import com.zongzewu.bettercallwu.service.SetmealDishService;
import com.zongzewu.bettercallwu.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * combo management
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
@Autowired
    private SetmealService setmealService;
@Autowired
    private SetmealDishService setmealDishService;
@Autowired
    private CategoryService categoryService;

@PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);
        return R.success("Successfully added new combo");
    }

    /**
     * combo paging query
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        //paging constructor
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage =new Page<>();


        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //blurry query according to name, like
        queryWrapper.like(name != null, Setmeal::getName, name);
        // sorting method, descending order by updatetime
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo, queryWrapper);

        // copy object's properties
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) ->{
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if(category != null){
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());
        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }

    /**
     * delte combo
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("ids: {}", ids);

        setmealService.removeWithDishes(ids);
        return R.success("Successfully deleted combo(s)");
    }

}
