package com.pph.demo.controller;

import com.pph.demo.annotation.SkipToken;
import com.pph.demo.service.GzdService;
import com.pph.demo.utils.common.ApiResult;
import com.pph.demo.vo.request.gzd.UploadImgReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author pph
 * @datetime 2020/8/5 20:04
 * @description
 */
@RestController
@RequestMapping("/gzd")
public class GzdController {

    @Autowired
    public GzdController(GzdService gzdService) {
        this.gzdService = gzdService;
    }

    private final GzdService gzdService;

    @RequestMapping(value = "/login")
    public Object login() {
        return ApiResult.success("ok");
    }

    @RequestMapping(value = "/img/add")
    public Object addImg(@RequestParam(value = "file") MultipartFile file, UploadImgReq req) {

        return gzdService.addImg(file, req);
    }

    @SkipToken
    @RequestMapping(value = "/img/query")
    public Object queryImg(UploadImgReq req) {

        return gzdService.queryImg(req);
    }

    @RequestMapping(value = "/img/delete/by/id")
    public Object deleteImgById(@RequestParam(name = "id") Long id) {

        return gzdService.deleteImgById(id);
    }

    @RequestMapping(value = "/img/update/url/by/id")
    public Object updateImgUrlById(@RequestParam(value = "file") MultipartFile file, UploadImgReq req) {

        return gzdService.updateImgUrlById(file, req);
    }
}
