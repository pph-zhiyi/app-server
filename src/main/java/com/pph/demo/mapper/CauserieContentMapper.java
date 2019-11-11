package com.pph.demo.mapper;

import com.pph.demo.model.CauserieContent;
import com.pph.demo.vo.response.causerie.QueryCauserieRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CauserieContentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CauserieContent record);

    int insertSelective(CauserieContent record);

    CauserieContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CauserieContent record);

    int updateByPrimaryKeyWithBLOBs(CauserieContent record);

    int updateByPrimaryKey(CauserieContent record);

    /**
     * 查询记录
     *
     * @param filter 入参
     * @return 结果
     */
    List<QueryCauserieRes> queryCauserieByTerms(Map<String, Object> filter);

    /**
     * 获取总条数
     *
     * @param filter 入参
     * @return 结果
     */
    Integer queryCountByTerms(Map<String, Object> filter);
}