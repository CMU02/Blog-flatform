package blog.flatform.dto.commentDto;

import blog.flatform.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListCommentsDto {
    private Long id;
    private Long postId;
    private String username;
    private String userEmail;
    private String content;

    public ListCommentsDto(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPost().getId();
        this.username = comment.getUser().getName();
        this.userEmail = comment.getUser().getEmail();
        this.content = comment.getContent();
    }
}
