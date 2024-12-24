package com.zongzewu.bettercallwu.controller;

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

import javax.servlet.http.HttpSession;

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
            return R.success("email sent");
        }

        return R.error("email not sent");
    }


}
