package com.pph.demo.service;

import com.pph.demo.model.GzdImg;
import com.pph.demo.vo.request.gzd.UploadImgReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author pph
 * @datetime 2020/8/5 20:05
 * @description
 */
public interface GzdService {

    /**
     * 上传图片
     *
     * @param file 文件
     * @param req  入参
     * @return oss url
     */
    GzdImg addImg(MultipartFile file, UploadImgReq req);

    /**
     * 查询图片
     *
     * @param req 入参
     * @return 结果
     */
    List<GzdImg> queryImg(UploadImgReq req);

    /**
     * 删除图片
     *
     * @param id ID
     * @return 结果
     */
    int deleteImgById(Long id);

    /**
     * 修改图片路径
     *
     * @param file 文件
     * @param req  入参
     * @return 结果
     */
    GzdImg updateImgUrlById(MultipartFile file, UploadImgReq req);
}
