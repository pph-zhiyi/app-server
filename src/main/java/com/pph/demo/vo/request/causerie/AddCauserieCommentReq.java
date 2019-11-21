package com.pph.demo.vo.request.causerie;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @Author: pph
 * @Date: 2019/11/20 19:26
 * @Description:
 */
@Data
public class AddCauserieCommentReq {

    private Long id;

    @NotNull(message = "contentId can not be null!")
    private Long contentId;

    @NotNull(message = "authorUser can not be null!")
    @NotBlank(message = "authorUser ca not be blank!")
    private String authorUser;

    @NotNull(message = "commentUser can not be null!")
    @NotBlank(message = "commentUser ca not be blank!")
    private String commentUser;

    @NotNull(message = "oldContent can not be null!")
    @NotBlank(message = "oldContent ca not be blank!")
    private String oldContent;

    @NotNull(message = "commentContent can not be null!")
    @NotBlank(message = "commentContent ca not be blank!")
    private String commentContent;

    @NotNull(message = "isContentAuthor can not be null!")
    private Boolean isContentAuthor;

    public static final class Builder {

        private Long contentId;

        private String authorUser;

        private String commentUser;

        private String oldContent;

        private String commentContent;

        private Boolean isContentAuthor;

        public static Builder init() {
            return new Builder();
        }

        public Builder contentId(Long contentId) {
            this.contentId = contentId;
            return this;
        }

        public Builder authorUser(String authorUser) {
            this.authorUser = authorUser;
            return this;
        }

        public Builder commentUser(String commentUser) {
            this.commentUser = commentUser;
            return this;
        }

        public Builder oldContent(String oldContent) {
            this.oldContent = oldContent;
            return this;
        }

        public Builder commentContent(String commentContent) {
            this.commentContent = commentContent;
            return this;
        }

        public Builder isContentAuthor(Boolean isContentAuthor) {
            this.isContentAuthor = isContentAuthor;
            return this;
        }

        public AddCauserieCommentReq builder() {
            AddCauserieCommentReq result = new AddCauserieCommentReq();
            result.setContentId(this.contentId);
            result.setAuthorUser(this.authorUser);
            result.setCommentUser(this.commentUser);
            result.setOldContent(this.oldContent);
            result.setCommentContent(this.commentContent);
            result.setIsContentAuthor(this.isContentAuthor);
            return result;
        }
    }
}
