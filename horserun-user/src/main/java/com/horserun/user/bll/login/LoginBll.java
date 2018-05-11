package com.horserun.user.bll.login;

import com.horserun.api.code.CommonCode;
import com.horserun.api.code.LoginCode;
import com.horserun.api.model.ReturnModel;
import com.horserun.user.dao.mysql.LoginMapper;
import com.horserun.user.dao.mysql.RegisterMapper;
import com.horserun.user.model.Guser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lxfang on 2018/3/22.
 */
@Service
public class LoginBll {

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    RegisterMapper registerMapper;

    public ReturnModel login(String loginName,String password){
        //首先验证该手机号是否存在对应用户
        int userExit = registerMapper.getUserByPho(loginName);
        if(userExit>0){
            List<String> psdList = loginMapper.getPassword(loginName);
            boolean bool = false;
            for(String psd:psdList){
                //密码验证通过
                if(psd.equals(DigestUtils.md5Hex(password))){
                    bool = true;
                }
            }
            if(bool){
                Guser guser = loginMapper.getUserByPho(loginName);
                return new ReturnModel(CommonCode.SUCCESS_CODE,guser,CommonCode.SUCCESS_MSG);
            }else{//密码验证不通过
                return new ReturnModel(LoginCode.LOGIN_ERROR_CODE,null,LoginCode.LOGIN_ERROR_MSG);
            }
        }else {//用户不存在
            return new ReturnModel(LoginCode.NOT_USER_CODE,-1,LoginCode.TNOT_USER_MSG);
        }
    }

}
