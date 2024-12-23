package com.zongzewu.bettercallwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zongzewu.bettercallwu.common.CustomException;
import com.zongzewu.bettercallwu.dto.SetmealDto;
import com.zongzewu.bettercallwu.entity.Setmeal;
import com.zongzewu.bettercallwu.entity.SetmealDish;
import com.zongzewu.bettercallwu.mapper.SetmealMapper;
import com.zongzewu.bettercallwu.service.SetmealDishService;
import com.zongzewu.bettercallwu.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * add new combo with its dishes
     */
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //save combo basic info, insert
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) ->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        //save its related info, insert

        setmealDishService.saveBatch(setmealDishes);

    }

    /**
     * delete combo and its related dishes
     * @param ids
     */
    @Transactional
    public void removeWithDishes(List<Long> ids) {
        //query combo status
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);
        //if it's not on sell, throw an exception
        int count = this.count(queryWrapper);
        if(count > 0){
            throw new CustomException("There is more than one combo is selling, could not be deleted");
        }

        //on selling then delete the data in combo table first
        this.removeByIds(ids);
        //then delete the data in combo relation table
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        //batch delete combo related dishes data
        setmealDishService.remove(lambdaQueryWrapper);
    }
}
