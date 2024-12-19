package com.zongzewu.bettercallwu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zongzewu.bettercallwu.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
