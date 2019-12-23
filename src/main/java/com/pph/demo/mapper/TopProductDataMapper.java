package com.pph.demo.mapper;

import com.pph.demo.model.TopProductData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TopProductDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopProductData record);

    int insertSelective(TopProductData record);

    TopProductData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TopProductData record);

    int updateByPrimaryKey(TopProductData record);

    int inserts(@Param(value = "lists") List<TopProductData> lists);
}