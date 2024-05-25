package blog.flatform.service.Impl;

import blog.flatform.config.SessionConst;
import blog.flatform.dto.loginDto.SessionUserDto;
import blog.flatform.dto.loginDto.SignUpDto;
import blog.flatform.dto.postDto.updatePostDto;
import blog.flatform.entity.Role;
import blog.flatform.entity.User;
import blog.flatform.message.ErrorMessage;
import blog.flatform.repository.UserRepository;
import blog.flatform.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
