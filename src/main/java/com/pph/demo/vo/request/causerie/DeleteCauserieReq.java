package com.pph.demo.vo.request.causerie;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @Author: pph
 * @Date: 2019/11/14 21:08
 * @Description:
 */
@Data
public class DeleteCauserieReq {

    @NotNull(message = "id can not be null!")
    private Long id;

    @NotNull(message = "content can not be null!")
    @NotBlank(message = "user ca not be blank!")
    private String user;

    public static final class Builder {

        private Long id;

        private String user;

        public static Builder init() {
            return new Builder();
        }

        public Builder content(Long id) {
            this.id = id;
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public DeleteCauserieReq builder() {
            DeleteCauserieReq result = new DeleteCauserieReq();
            result.setId(this.id);
            result.setUser(this.user);
            return result;
        }
    }
}
