package com.zongzewu.bettercallwu.dto;

import com.zongzewu.bettercallwu.entity.Dish;
import com.zongzewu.bettercallwu.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
