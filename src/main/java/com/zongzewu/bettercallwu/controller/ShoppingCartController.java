package com.zongzewu.bettercallwu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zongzewu.bettercallwu.common.BaseContext;
import com.zongzewu.bettercallwu.common.R;
import com.zongzewu.bettercallwu.entity.ShoppingCart;
import com.zongzewu.bettercallwu.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;


    /**
     * add into shopping cart
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        log.info("Shopping cart data: {}", shoppingCart);
        //set userId, represents which user is added this dish to his shopping cart


        //query if this dish is already in user's shopping cart,
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        Long dishId = shoppingCart.getDishId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentId);

        if(dishId != null){
            //this is a dish that now being added to the shopping cart
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        }else{
            // it is a combo that being added to the shopping cart
            queryWrapper.eq(ShoppingCart::getDishId, shoppingCart.getSetmealId());
        }
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);
        if(cartServiceOne != null){
            //if so, increment 1 on this dish
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            shoppingCartService.updateById(cartServiceOne);
        }else{
            // if not, add one of this dish to shopping cart
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }

        return R.success(cartServiceOne);
    }
    /**
     * query shopping cart
     */
@GetMapping("/list")
    public R<List<ShoppingCart>> list(){
    LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
    queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
    List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
    return R.success(list);
    }

    @DeleteMapping("/clean")
    public R<String> clear(){
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());

        shoppingCartService.remove(queryWrapper);

        return R.success("shopping cart cleared");
    }
}
