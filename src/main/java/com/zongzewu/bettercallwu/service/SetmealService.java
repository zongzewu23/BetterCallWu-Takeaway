package com.zongzewu.bettercallwu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zongzewu.bettercallwu.dto.SetmealDto;
import com.zongzewu.bettercallwu.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * add new combo with its dishes
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * delete combo and its related dishes
     * @param ids
     */
    public void removeWithDishes(List<Long> ids);
}
