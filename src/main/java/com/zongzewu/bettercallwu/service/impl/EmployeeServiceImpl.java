package com.zongzewu.bettercallwu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zongzewu.bettercallwu.entity.Employee;
import com.zongzewu.bettercallwu.mapper.EmployeeMapper;
import com.zongzewu.bettercallwu.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
