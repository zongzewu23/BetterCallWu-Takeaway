package com.zongzewu.bettercallwu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zongzewu.bettercallwu.entity.Orders;

public interface OrderService extends IService<Orders> {
    /**
     * order placement
     * @param orders
     */
    public void submit(Orders orders);
}
