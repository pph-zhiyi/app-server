package com.pph.demo.vo.response.causerie;

import com.pph.demo.model.CauserieContentComment;
import com.pph.demo.model.CauserieContentLike;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author pph
 * @date 2019/11/11 18:06
 * @description
 */
@Data
public class QueryCauserieRes {

    private Long id;

    private String authorUser;

    private String authorName;

    private String content;

    private Date commitTime;

    private List<CauserieContentLike> likes;

    private List<CauserieContentComment> comments;
}
