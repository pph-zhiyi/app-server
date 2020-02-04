package com.pph.demo.mapper;

import com.pph.demo.model.UserImg;

public interface UserImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserImg record);

    int insertSelective(UserImg record);

    UserImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserImg record);

    int updateByPrimaryKey(UserImg record);
}