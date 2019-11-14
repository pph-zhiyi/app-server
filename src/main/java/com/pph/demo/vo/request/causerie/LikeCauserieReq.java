package com.pph.demo.vo.request.causerie;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @Author: pph
 * @Date: 2019/11/12 09:16
 * @Description:
 */
@Data
public class LikeCauserieReq {

    @NotNull(message = "contentId can not be null!")
    private Integer contentId;

    @NotNull(message = "user can not be null!")
    @NotBlank(message = "user ca not be blank!")
    private String user;

    private Integer like;

    public static final class Builder {

        private Integer contentId;

        private String user;

        private Integer like;

        public static Builder init() {
            return new Builder();
        }

        public Builder contentId(Integer contentId) {
            this.contentId = contentId;
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public Builder like(Integer like) {
            this.like = like;
            return this;
        }

        public LikeCauserieReq builder() {
            LikeCauserieReq result = new LikeCauserieReq();
            result.setContentId(this.contentId);
            result.setUser(this.user);
            result.setLike(this.like);
            return result;
        }
    }
}
