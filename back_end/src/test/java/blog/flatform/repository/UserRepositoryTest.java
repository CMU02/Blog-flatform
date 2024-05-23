package blog.flatform.repository;

import blog.flatform.entity.Role;
import blog.flatform.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByEmail() {
        User user = User.builder()
                .name("test")
                .email("test@test.com")
                .password("test")
                .role(Role.USER)
                .build();

        userRepository.save(user);
        User findUser = userRepository.findByEmail(user.getEmail()).orElse(null);

        assertThat(user).isEqualTo(findUser);
        assertThat(user.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void findAll() {
        User user1 = User.builder()
                .name("test1")
                .email("test1@test.com")
                .password("test1")
                .role(Role.USER)
                .build();
        userRepository.save(user1);

        User user2 = User.builder()
                .name("test2")
                .email("test2@test.com")
                .password("test2")
                .role(Role.USER)
                .build();
        userRepository.save(user2);

        List<User> userList = userRepository.findAll();

        assertThat(userList).hasSize(4); // Config/InitDB.class 테스트 계정으로 인한 크기 4
    }
}