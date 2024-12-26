package com.zongzewu.bettercallwu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zongzewu.bettercallwu.common.R;
import com.zongzewu.bettercallwu.entity.User;
import com.zongzewu.bettercallwu.service.UserService;
import com.zongzewu.bettercallwu.utils.EmailSender;
import com.zongzewu.bettercallwu.utils.VerificationCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        String email = user.getEmail();
        if(StringUtils.isNotEmpty(email)){
            //get code
            String code = VerificationCodeGenerator.generateCode();
                log.info("code = {}", code);
            EmailSender.sendEmail(email, code);

            session.setAttribute(email, code);
            return R.success("email is now sent");
        }

        return R.error("email is not sent");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){
       log.info(map.toString());
       //get email
        String email = map.get("email").toString();
        //get code
        String code = map.get("code").toString();
        //get verification code from session
        Object verifyCodeInSession = session.getAttribute(email);
        //compare code and verification code
        if(verifyCodeInSession != null && verifyCodeInSession.equals(code)){


            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getEmail, email);
            User user = userService.getOne(queryWrapper);
            if(user == null){
                //equal but does not exist in user table, insert this user in user table
                user = new User();
                user.setEmail(email);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());
                //equal and exists in user table, login successful
                return R.success(user);
        }
        // not equal
        return R.error("Login Failed");
    }
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");

        return R.success("Successfully logged out");
    }
}
