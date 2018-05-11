package com.horserun.user.bll.register;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.horserun.api.code.CommonCode;
import com.horserun.api.code.RegisterCode;
import com.horserun.api.code.SendMnsCode;
import com.horserun.api.model.ReturnModel;
import com.horserun.user.dao.mysql.RegisterMapper;
import com.horserun.user.model.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lxfang on 2018/3/16.
 */
@Service
public class RegisterBll {
    @Autowired
    RegisterMapper registerMapper;

    @Value("${mns.accessKeyId}")
    private String accessKeyId;

    @Value("${mns.accessKeySecret}")
    private String accessKeySecret;

    @Value("${mns.SignName}")
    private String SignName;

    @Value("${mns.TemplateCode}")
    private String TemplateCode;

    public ReturnModel registerUser(RegisterUser registerUser, String code){
        String phoNum = registerUser.getPhoNum();
        //验证短信注册码
        String rightCode = registerMapper.getCodeByPhoNum(phoNum);
        if(code.equals(rightCode)){//短信验证码验证通过
            int result = registerMapper.register(registerUser);
            return new ReturnModel(CommonCode.SUCCESS_CODE,result,CommonCode.SUCCESS_MSG);
        }else {//短信验证码验证不通过
            return new ReturnModel(RegisterCode.CODE_ERROR_CODE,-1,RegisterCode.CODE_ERROR_MSG);
        }
    }

    public int userExit(@RequestParam(value = "phoNum")String phoNum){
        return registerMapper.getUserByPho(phoNum);
    }

    public int mnsExit(@RequestParam(value = "phoNum")String phoNum){
        return registerMapper.mnsExit(phoNum);
    }

    public int insertSmsCode(@RequestParam(value = "phoNum")String phoNum,
                             @RequestParam(value = "code")String code){
        return registerMapper.insertSmsCode(phoNum,code);
    }

    public int updateSmsCode(@RequestParam(value = "phoNum")String phoNum,
                             @RequestParam(value = "code")String code){
        return registerMapper.updateSmsCode(phoNum,code);
    }

    public ReturnModel sendMns(@RequestParam(value = "phoneNumber")String phoneNumber,
                               @RequestParam(value = "msg")String msg) throws ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        //final String accessKeyId = "LTAIa6QqCPzEybtM";//你的accessKeyId,参考本文档步骤2
        //final String accessKeySecret = "UDc9eBVpKYnTwpXkHgSvm2r0T4Iq1W";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("jevic服务端警报");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(TemplateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败

        request.setTemplateParam("{\"detailMsg\":\""+msg+"\"}");

        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者

        //request.setOutId("yourOutId");

        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        int result;
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            result = 1;
            return new ReturnModel(SendMnsCode.SUCCESS_CODE,result,SendMnsCode.SUCCESS_MSG);
        }else {
            //请求失败
            result = -1;
            return new ReturnModel(SendMnsCode.FAIL_CODE,result,SendMnsCode.FAIL_MSG);
        }
    }

}
