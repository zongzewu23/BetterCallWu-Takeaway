package com.zongzewu.bettercallwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zongzewu.bettercallwu.common.CustomException;
import com.zongzewu.bettercallwu.entity.Category;
import com.zongzewu.bettercallwu.entity.Dish;
import com.zongzewu.bettercallwu.entity.Setmeal;
import com.zongzewu.bettercallwu.mapper.CategoryMapper;
import com.zongzewu.bettercallwu.service.CategoryService;
import com.zongzewu.bettercallwu.service.DishService;
import com.zongzewu.bettercallwu.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;
    /**
     * remove category by id, but check if the dish or combo has concatenated with others, if so don't remove
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        //query if this category has already related to other dishes, if so , throw an exception
        if(count1 > 0){
            //related, throw exception
            throw new CustomException("There are dishes associated with the current category therefore cannot be deleted.");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if(count2 > 0){
            //related to a combo, throw exception
            throw new CustomException("There are Combo associated with the current category therefore cannot be deleted.");
        }
        // if don't then remove this category
        super.removeById(id);
    }
}
