package com.zongzewu.bettercallwu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zongzewu.bettercallwu.common.R;
import com.zongzewu.bettercallwu.entity.Employee;
import com.zongzewu.bettercallwu.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * employee login
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        //MD5 encrypted password
        String password = employee.getPassword();
        password =  DigestUtils.md5DigestAsHex(password.getBytes());

        //Query the database based on the username submitted on the page
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //If no query is found, the login failure result is returned
        if(emp == null){
            return R.error("login failed");
        }
        //Compare password
        if(!emp.getPassword().equals(password)){
            return R.error("login failed");
        }
        //Check if the employee's account is disabled
        if(emp.getStatus() == 0){
            return R.error("Account has been disabled");
        }
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * employee logout
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout (HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("logout succeed");
    }

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("add employee : {}", employee.toString());
        //set initial password with MD5 encryption
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //Long empId = (long) request.getSession().getAttribute("employee");
        //employee.setCreateUser(empId);
        //employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("New employee added successfully");
    }

    /**
     * Employee information paging query
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {}, pageSize = {}, name = {}", page, pageSize, name);
        //construct paging constructor
        Page pageInfo = new Page(page, pageSize);

        //conditional paging constructor
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(!StringUtils.isEmpty(name), Employee::getName, name);
        //Add sorting criteria
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //execute query
        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * modify employee info by id
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){
        log.info(employee.toString());
        long id = Thread.currentThread().getId();
        log.info("ID is: {}", id);
        //Long empId = (Long) request.getSession().getAttribute(("employee"));
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser(empId);
        employeeService.updateById(employee);

        return R.success("Employee information modified successfully");
    }

    /**
     * query employee info by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("query employee info by id");
        Employee employee = employeeService.getById(id);
        if(employee != null){
            return  R.success(employee);
        }
        return R.error("No employee information found");
    }
}
