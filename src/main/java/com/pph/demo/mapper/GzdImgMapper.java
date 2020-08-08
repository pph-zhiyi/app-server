package com.pph.demo.mapper;

import com.pph.demo.model.GzdImg;
import com.pph.demo.vo.request.gzd.UploadImgReq;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GzdImgMapper {

    /**
     * 新增
     *
     * @param img 入参
     * @return 结果
     */
    int add(GzdImg img);

    /**
     * 查询
     *
     * @param req 入参
     * @return 结果
     */
    List<GzdImg> queryImg(UploadImgReq req);

    /**
     * 删除
     *
     * @param id ID
     * @return 结果
     */
    @Delete("DELETE FROM `gzd_img` WHERE `id` = #{id}")
    int deleteById(@Param(value = "id") Long id);

    @Update("UPDATE `gzd_img` SET `url` = #{url} WHERE `id` = #{id}")
    int updateImgUrlById(@Param(value = "id") Long id,
                         @Param(value = "url") String url);

    @Delete("SELECT * FROM `gzd_img` WHERE `id` = #{id}")
    GzdImg getById(Long id);
}