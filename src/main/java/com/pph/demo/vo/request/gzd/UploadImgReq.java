package com.pph.demo.vo.request.gzd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pph
 * @datetime 2020/8/6 22:03
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadImgReq {

    private Long id;

    private String name;

    private String group;

    private String url;

    private Integer index;

    private String description;
}
