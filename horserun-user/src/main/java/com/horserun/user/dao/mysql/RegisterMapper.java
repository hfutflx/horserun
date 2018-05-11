package com.horserun.user.dao.mysql;

import com.horserun.user.model.RegisterUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lxfang on 2018/3/16.
 */
@Mapper
public interface RegisterMapper {
    Integer getUserByPho(@Param("phoNum")String phoNum);
    int register(@Param("RegisterUser")RegisterUser registerUser);
    int mnsExit(@Param("phoNum")String phoNum);
    int insertSmsCode(@Param("phoNum")String phoNum,@Param("code")String code);
    int updateSmsCode(@Param("phoNum")String phoNum,@Param("code")String code);
    String getCodeByPhoNum(@Param("phoNum")String phoNum);
}
