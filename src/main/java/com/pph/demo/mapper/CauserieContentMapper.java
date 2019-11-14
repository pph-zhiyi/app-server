package com.pph.demo.mapper;

import com.pph.demo.model.CauserieContent;
import com.pph.demo.model.CauserieContentLike;
import com.pph.demo.vo.request.causerie.AddCauserieReq;
import com.pph.demo.vo.request.causerie.DeleteCauserieReq;
import com.pph.demo.vo.request.causerie.LikeCauserieReq;
import com.pph.demo.vo.response.causerie.QueryCauserieRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 根据 contentId & user 查询 like 记录
     *
     * @param req 入参
     * @return 结果
     */
    CauserieContentLike queryLikeByUser(LikeCauserieReq req);

    /**
     * 根据 contentId & user 查询 like 记录
     *
     * @param req 入参
     * @return 结果
     */
    Integer deleteLikeByUser(LikeCauserieReq req);

    /**
     * 根据 contentId & user 修改 like 记录
     *
     * @param req 入参
     * @return 结果
     */
    Integer updateLikeByUser(LikeCauserieReq req);

    /**
     * 新增点赞
     *
     * @param req 入参
     * @return 结果
     */
    Integer addLikeByUser(LikeCauserieReq req);

    /**
     * 新增记录
     *
     * @param req 入参
     * @return 结果
     */
    Integer addCauserie(AddCauserieReq req);

    /**
     * 删除记录
     *
     * @param req 入参
     * @return 结果
     */
    Integer deleteCauserie(DeleteCauserieReq req);

    /**
     * 根据 id & user 查询记录
     *
     * @param id   id
     * @param user user
     * @return 结果
     */
    @Select("SELECT * FROM `causerie_content` WHERE `id` = #{id} AND `user` = #{user}")
    CauserieContent queryContentByIdUser(@Param(value = "id") Long id,
                                         @Param(value = "user") String user);
}