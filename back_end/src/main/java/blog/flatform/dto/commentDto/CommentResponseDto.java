package blog.flatform.dto.commentDto;

import blog.flatform.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long postId;
    private String userEmail;
    private String userName;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.postId = comment.getPost().getId();
        this.userEmail = comment.getUser().getEmail();
        this.userName = comment.getUser().getName();
    }
}
