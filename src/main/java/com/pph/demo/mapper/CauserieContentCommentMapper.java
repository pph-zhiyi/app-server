package com.pph.demo.mapper;

import com.pph.demo.model.CauserieContentComment;

public interface CauserieContentCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CauserieContentComment record);

    int insertSelective(CauserieContentComment record);

    CauserieContentComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CauserieContentComment record);

    int updateByPrimaryKeyWithBLOBs(CauserieContentComment record);

    int updateByPrimaryKey(CauserieContentComment record);
}