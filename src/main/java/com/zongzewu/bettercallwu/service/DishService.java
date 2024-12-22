package com.zongzewu.bettercallwu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zongzewu.bettercallwu.dto.DishDto;
import com.zongzewu.bettercallwu.entity.Dish;

public interface DishService extends IService<Dish> {

    //add dish and insert dish flavor,  need to operate two table: dish and dish_flavor
    public void saveWithFlavor(DishDto dishDto);

    //get dish info and it's flavor by id
    public  DishDto getByIdWithFlavor(Long id);

    //update dish info and it's flavor
    public void updateWithFlavor(DishDto dishDto);
}
