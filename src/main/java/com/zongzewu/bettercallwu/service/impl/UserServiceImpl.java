package com.zongzewu.bettercallwu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zongzewu.bettercallwu.entity.User;
import com.zongzewu.bettercallwu.mapper.UserMapper;
import com.zongzewu.bettercallwu.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
