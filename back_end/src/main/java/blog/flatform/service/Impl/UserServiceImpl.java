package blog.flatform.service.Impl;

import blog.flatform.dto.SignInDto;
import blog.flatform.dto.SignUpDto;
import blog.flatform.entity.Role;
import blog.flatform.entity.User;
import blog.flatform.repository.UserRepository;
import blog.flatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // 회원 가입
    @Transactional
    public Long signUp(SignUpDto signUpDto) {
        User user = User.builder()
                .name(signUpDto.getName())
                .password(signUpDto.getPassword())
                .email(signUpDto.getEmail())
                .role(Role.USER)
                .build();
        return userRepository.save(user).getId();
    }

    /**
     * 로그인
     * @return null이면 로그인 실패
     */
    @Override
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    // 회원 전체 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }
}
