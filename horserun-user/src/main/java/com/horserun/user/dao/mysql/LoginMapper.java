package com.horserun.user.dao.mysql;

import com.horserun.user.model.Guser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lxfang on 2018/3/22.
 */
@Mapper
public interface LoginMapper {
    List<String> getPassword(@Param("loginName")String loginName);
    int login(@Param("loginName")String loginName,@Param("password")String password);
    Guser getUserByPho(@Param("loginName")String loginName);
}
