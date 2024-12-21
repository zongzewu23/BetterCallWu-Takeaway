package com.zongzewu.bettercallwu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zongzewu.bettercallwu.dto.DishDto;
import com.zongzewu.bettercallwu.entity.Dish;
import com.zongzewu.bettercallwu.entity.DishFlavor;
import com.zongzewu.bettercallwu.mapper.DishMapper;
import com.zongzewu.bettercallwu.service.DishFlavorService;
import com.zongzewu.bettercallwu.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    /**
     * add dish and it's flavor
     * @param dishDto
     */
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //save dish info do dish table
        this.save(dishDto);
        Long dishId = dishDto.getId();
        //dish flavor
        List<DishFlavor> flavors = dishDto.getFlavors();
      flavors = flavors.stream().map((item) ->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        //save flavor to dish_flavor
        dishFlavorService.saveBatch(flavors);

    }
}
