package com.pph.demo.service.impl;

import com.pph.demo.mapper.GzdImgMapper;
import com.pph.demo.model.GzdImg;
import com.pph.demo.service.GzdService;
import com.pph.demo.utils.OssUtil;
import com.pph.demo.vo.request.gzd.UploadImgReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author pph
 * @datetime 2020/8/5 20:05
 * @description
 */
@Service
public class GzdServiceImpl implements GzdService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GzdServiceImpl.class);

    @Autowired
    public GzdServiceImpl(GzdImgMapper gzdImgMapper) {
        this.gzdImgMapper = gzdImgMapper;
    }

    private final GzdImgMapper gzdImgMapper;

    @Override
    public GzdImg addImg(MultipartFile file, UploadImgReq req) {
        LOGGER.info("*** addImg req: {}", req);

        String fileName = getFileName(file);

        try {
            String url = uploadToOss(file);

            GzdImg img = GzdImg.builder()
                    .name(fileName)
                    .group(req.getGroup())
                    .index(req.getIndex())
                    .url(url).build();
            LOGGER.info("*** addImg img: {}", img);

            int count = gzdImgMapper.add(img);
            LOGGER.info("*** addImg count: {}", count);

            if (count > 0) {
                return img;
            } else {
                throw new RuntimeException("新增图片失败!");
            }
        } catch (Exception e) {
            LOGGER.error("addImg error: {}", e.getMessage());
            throw new RuntimeException("文件上传失败, error: " + e.getMessage());
        }
    }

    @Override
    public List<GzdImg> queryImg(UploadImgReq req) {
        LOGGER.info("*** queryImg req: {}", req);
        return gzdImgMapper.queryImg(req);
    }

    @Override
    public int deleteImgById(Long id) {
        LOGGER.info("*** deleteImgById id: {}", id);
        return gzdImgMapper.deleteById(id);
    }

    @Override
    public GzdImg updateImgUrlById(MultipartFile file, UploadImgReq req) {
        LOGGER.info("*** updateImgUrlById req: {}", req);

        try {
            String url = uploadToOss(file);
            LOGGER.info("*** updateImgUrlById url: {}", url);

            int count = gzdImgMapper.updateImgUrlById(req.getId(), url);
            LOGGER.info("*** updateImgUrlById count: {}", count);

            if (count > 0) {
                return gzdImgMapper.getById(req.getId());
            } else {
                throw new RuntimeException("修改图片失败!");
            }
        } catch (Exception e) {
            LOGGER.error("updateImgUrlById error: {}", e.getMessage());
            throw new RuntimeException("文件上传失败, error: " + e.getMessage());
        }
    }

    private String getFileName(MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            System.out.println("文件为空空");
        }

        // 文件名
        String fileName = Objects.requireNonNull(file.getOriginalFilename(), "文件名为空!");
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        return fileName;
    }

    public String uploadToOss(MultipartFile file) {
        String fileName = getFileName(file);

        try {
            return OssUtil.uploadFile("gzd-img", fileName, file.getInputStream());
        } catch (Exception e) {
            LOGGER.error("uploadToOss error: {}", e.getMessage());
            throw new RuntimeException("文件上传 oss 失败, error: " + e.getMessage());
        }
    }
}
