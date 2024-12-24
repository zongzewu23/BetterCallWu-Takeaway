package com.zongzewu.bettercallwu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zongzewu.bettercallwu.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
