package blog.flatform.dto.commentDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateRequestDto {
    private Long id;
    private Long postId;
    private String content;
}
