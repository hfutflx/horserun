package com.horserun.core.core.controller;

import com.horserun.api.model.ReturnModel;
import com.horserun.api.service.core.RegisService;
import com.horserun.api.service.user.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by lxfang on 2018/3/17.
 */
@Controller
@RequestMapping("/register")
public class RegisController implements RegisService{

    @Autowired
    RegisterService registerService;

    @Override
    @RequestMapping("/registerUser")
    @ResponseBody
    public ReturnModel registerUser(@RequestParam(value = "phoNum")String phoNum,
                                    @RequestParam(value = "loginName")String loginName,
                                    @RequestParam(value = "password")String password,
                                    @RequestParam(value = "userType")int userType,
                                    @RequestParam(value = "code")String code){
        return registerService.registerUser(phoNum, loginName, password, userType, code);
    }
}
