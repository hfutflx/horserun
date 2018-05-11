package com.horserun.user.controller;


import com.aliyuncs.exceptions.ClientException;
import com.horserun.api.code.RegisterCode;
import com.horserun.api.code.SendMnsCode;
import com.horserun.api.model.ReturnModel;
import com.horserun.api.service.user.RegisterService;

import com.horserun.user.bll.register.RegisterBll;
import com.horserun.user.model.RegisterUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

/**
 * Created by lxfang on 2018/3/19.
 */
@RestController
@RequestMapping("/register")
public class RegisterController implements RegisterService {

    @Autowired
    RegisterBll registerBll;

    /**
     * 注册用户
     * @param phoNum 手机号
     * @param loginName 登陆名
     * @param password 密码
     * @param userType 用户类型
     * @param code 注册码
     * @return
     */
    @Override
    @ResponseBody
    public ReturnModel registerUser(@RequestParam(value = "phoNum")String phoNum,
                                    @RequestParam(value = "loginName")String loginName,
                                    @RequestParam(value = "password")String password,
                                    @RequestParam(value = "userType")int userType,
                                    @RequestParam(value = "code")String code){
        String uuid = UUID.randomUUID().toString();
        RegisterUser registerUser = new RegisterUser();
        registerUser.setId(uuid);
        registerUser.setPhoNum(phoNum);
        registerUser.setLoginName(loginName);
        registerUser.setPassword(DigestUtils.md5Hex(password));
        registerUser.setUserType(userType);
        return registerBll.registerUser(registerUser,code);
    }

    /**
     * 发送短信注册码
     * @param phoNum 手机号
     * @return
     * @throws ClientException
     */
    @Override
    @ResponseBody
    public ReturnModel sendMns(@RequestParam(value = "phoNum")String phoNum) throws ClientException {
        //先判断手机号是否注册过了
        int userExist = registerBll.userExit(phoNum);
        if(userExist>0){//用户存在
            return new ReturnModel(RegisterCode.USEREXIT_CODE,-1,RegisterCode.USEREXIT_MSG);
        }else {//用户不存在
            String code = String.valueOf(new Random().nextInt(899999) + 100000);
            //判断短信注册码是否存在
            int mnsExist = registerBll.mnsExit(phoNum);
            int mnsResult = 0;
            if(mnsExist>0){
                //短信注册码已存在，则更新注册码
                mnsResult = registerBll.updateSmsCode(phoNum,code);
            }else {
                //短信注册码不存在，则插入注册码
                mnsResult = registerBll.insertSmsCode(phoNum,code);
            }
            //数据库更新注册码成功（更新包括初次插入以及后期更新）
            if(mnsResult>0){
                return registerBll.sendMns(phoNum,code);
            }else {//数据库更新注册码失败
                return new ReturnModel(RegisterCode.CREATECODE_ERROR_CODE,-1,RegisterCode.CREATECODE_ERROR_MSG);
            }
        }
    }
}
