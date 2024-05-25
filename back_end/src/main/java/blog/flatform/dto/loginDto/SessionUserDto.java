package blog.flatform.dto.loginDto;

import blog.flatform.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SessionUserDto {
    private String username;
    private String userEmail;

    public SessionUserDto(String username, String userEmail) {
        this.username = username;
        this.userEmail = userEmail;
    }
}
