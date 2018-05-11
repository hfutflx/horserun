package com.horserun.core.core.controller;

import com.horserun.api.model.ReturnModel;
import com.horserun.api.service.core.LgService;
import com.horserun.api.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lxfang on 2018/3/30.
 */
@Controller
@RequestMapping("/login")
public class LgController implements LgService{

    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public ReturnModel login(@RequestParam(value = "loginName")String loginName,
                             @RequestParam(value = "password")String password){
        return loginService.login(loginName,password);
    }

}
