package com.pph.demo.mapper;

import com.pph.demo.model.CauserieContentLike;

public interface CauserieContentLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CauserieContentLike record);

    int insertSelective(CauserieContentLike record);

    CauserieContentLike selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CauserieContentLike record);

    int updateByPrimaryKey(CauserieContentLike record);
}