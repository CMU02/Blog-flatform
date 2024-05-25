package blog.flatform.dto.postDto;

import blog.flatform.entity.Category;
import blog.flatform.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class savePostDto {
    private String title;
    private String content;
    private User user;
    private Category category;
}
