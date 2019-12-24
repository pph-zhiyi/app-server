package com.pph.demo.mapper;

import com.pph.demo.model.TopProductData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TopProductDataMapper {
    /**
     * 新增
     *
     * @param record 入参
     * @return 影响行数
     */
    int insertSelective(TopProductData record);

    /**
     * 修改
     *
     * @param record 入参
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(TopProductData record);

    /**
     * 批量新增（`model` 已存在则更新当前数据的其他字段，不存在则插入数据 ）
     *
     * @param lists 入参集合
     * @return 影响行数
     */
    int adds(@Param(value = "lists") List<TopProductData> lists);

    /**
     * 查询产品数据
     *
     * @param filter 过滤条件
     * @return 结果
     */
    List<TopProductData> queryProductByTerms(Map<String, Object> filter);

    /**
     * 查询数量
     *
     * @param filter 过滤条件
     * @return 结果
     */
    int queryTotalByTerms(Map<String, Object> filter);

    @Select("SELECT DISTINCT(`function`) FROM `top_product_data`")
    List<String> distinctFunction();

    @Select("SELECT DISTINCT(`standard_type`) FROM `top_product_data`")
    List<String> distinctStandardType();

    @Select("SELECT DISTINCT(`manufacturer`) FROM `top_product_data`")
    List<String> distinctManufacturer();
}