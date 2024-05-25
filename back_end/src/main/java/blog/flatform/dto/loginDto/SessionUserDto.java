package blog.flatform.dto.loginDto;

import blog.flatform.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SessionUserDto {
    private Long userIndex;
    private String username;
    private String userEmail;

    public SessionUserDto(Long userIndex, String username, String userEmail) {
        this.userIndex = userIndex;
        this.username = username;
        this.userEmail = userEmail;
    }
}
