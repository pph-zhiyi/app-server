package com.pph.demo.mapper;

import com.pph.demo.model.LoginLogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLogInfo record);

    int insertSelective(LoginLogInfo record);

    LoginLogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLogInfo record);

    int updateByPrimaryKey(LoginLogInfo record);

    /**
     * 插入
     *
     * @param login
     * @return
     */
    Integer save(LoginLogInfo login);
}