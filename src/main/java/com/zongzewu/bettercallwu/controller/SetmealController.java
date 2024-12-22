package com.zongzewu.bettercallwu.controller;

import com.zongzewu.bettercallwu.service.SetmealDishService;
import com.zongzewu.bettercallwu.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
