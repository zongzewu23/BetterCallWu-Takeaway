package com.zongzewu.bettercallwu.dto;

import com.zongzewu.bettercallwu.entity.Setmeal;
import com.zongzewu.bettercallwu.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
