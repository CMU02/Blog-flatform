package blog.flatform.config;

import blog.flatform.entity.Role;
import blog.flatform.entity.User;
import blog.flatform.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        User tester = User.builder()
                .name("test1")
                .password("1234")
                .email("test@naver.com")
                .role(Role.USER)
                .build();
        User admin = User.builder()
                .name("admin")
                .password("admin9570")
                .email("admin@naver.com")
                .role(Role.ADMIN)
                .build();

        userRepository.save(tester);
        userRepository.save(admin);
    }
}
