package com.pph.demo.vo.request.causerie;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @author pph
 * @date 2019/11/13 13:49
 * @Description:
 */
@Data
public class AddCauserieReq {

    @NotNull(message = "content can not be null!")
    @NotBlank(message = "user ca not be blank!")
    private String content;

    @NotNull(message = "content can not be null!")
    @NotBlank(message = "user ca not be blank!")
    private String user;

    public static final class Builder {

        private String content;

        private String user;

        public static Builder init() {
            return new Builder();
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public AddCauserieReq builder() {
            AddCauserieReq result = new AddCauserieReq();
            result.setContent(this.content);
            result.setUser(this.user);
            return result;
        }
    }
}
