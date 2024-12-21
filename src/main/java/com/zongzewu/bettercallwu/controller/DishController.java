package com.zongzewu.bettercallwu.controller;

import com.zongzewu.bettercallwu.common.R;
import com.zongzewu.bettercallwu.dto.DishDto;
import com.zongzewu.bettercallwu.service.DishFlavorService;
import com.zongzewu.bettercallwu.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * dish management
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * add new dish
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
      log.info(dishDto.toString());

      dishService.saveWithFlavor(dishDto);
        return R.success("Successfully added new dish");
    }
}
