package blog.flatform.dto.postDto;

import blog.flatform.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class findPostDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private String categoryName;

    public findPostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getName();
        this.categoryName = post.getCategory().getName();
    }
}
