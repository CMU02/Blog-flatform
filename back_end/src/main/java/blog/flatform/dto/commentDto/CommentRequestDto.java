package blog.flatform.dto.commentDto;

import blog.flatform.dto.loginDto.SessionUserDto;
import blog.flatform.entity.Comment;
import blog.flatform.entity.Post;
import blog.flatform.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String content;
    private Long postId;
    private User user;

    public Comment toEntity(CommentRequestDto dto) {
        return Comment.builder()
                .content(dto.getContent())
                .user(dto.getUser())
                .post(Post.builder()
                        .id(dto.getPostId())
                        .build())
                .build();
    }
}
