package com.horserun.user.controller;


import com.horserun.api.model.ReturnModel;
import com.horserun.api.service.user.LoginService;
import com.horserun.user.bll.login.LoginBll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lxfang on 2018/3/22.
 */
@RestController
@RequestMapping("/login")
public class LoginController implements LoginService{

    @Autowired
    LoginBll loginBll;

    @RequestMapping("/login")
    public ReturnModel login(@RequestParam(value = "loginName")String loginName,
                             @RequestParam(value = "password")String password){
        return loginBll.login(loginName,password);
    }
}
