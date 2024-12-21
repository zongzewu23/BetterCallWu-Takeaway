package com.zongzewu.bettercallwu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zongzewu.bettercallwu.entity.DishFlavor;
import com.zongzewu.bettercallwu.mapper.DishFlavorMapper;
import com.zongzewu.bettercallwu.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
