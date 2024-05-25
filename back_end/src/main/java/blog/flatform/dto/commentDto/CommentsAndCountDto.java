package blog.flatform.dto.commentDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsAndCountDto<T> {
    private int commentCount;
    private T commentData;

    public CommentsAndCountDto(int commentCount, T commentData) {
        this.commentCount = commentCount;
        this.commentData = commentData;
    }
}
