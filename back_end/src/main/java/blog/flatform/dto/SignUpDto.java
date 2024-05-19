package blog.flatform.dto;

import blog.flatform.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    private String name;
    private String email;
    private String password;
}
