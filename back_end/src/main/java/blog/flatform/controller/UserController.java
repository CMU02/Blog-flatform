package blog.flatform.controller;

import blog.flatform.config.SessionConst;
import blog.flatform.dto.SignInDto;
import blog.flatform.dto.SignUpDto;
import blog.flatform.entity.User;
import blog.flatform.message.ErrorMessage;
import blog.flatform.message.SuccessfulMessage;
import blog.flatform.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignUpDto signUpDto) {
        userService.signUp(signUpDto);
        return ResponseEntity.status(200).body(new SuccessfulMessage("회원 가입 성공"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody SignInDto signInDto, HttpServletRequest request) {
        User loginUser = userService.login(signInDto.getEmail(), signInDto.getPassword());

        if (loginUser == null) {
            return ResponseEntity.
                    status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorMessage("아이디 또는 비밀번호가 맞지 않습니다."));
        }

        // 세션이 있으면 세션 반환, 없을 시 세션 신규 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 아이디 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessfulMessage("로그인 성공"));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null || session.getAttribute(SessionConst.LOGIN_USER) != null) {
            session.invalidate();
        }

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessfulMessage("로그아웃 성공"));
    }
}
