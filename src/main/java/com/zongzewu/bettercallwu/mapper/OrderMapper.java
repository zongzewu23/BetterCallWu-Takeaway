package com.zongzewu.bettercallwu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zongzewu.bettercallwu.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
