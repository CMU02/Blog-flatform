package blog.flatform.dto.postDto;

import blog.flatform.entity.Category;
import blog.flatform.entity.Post;
import blog.flatform.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class findAllPostDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private String categoryName;

    public findAllPostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getName();
        this.categoryName = post.getCategory().getName();
    }
}


