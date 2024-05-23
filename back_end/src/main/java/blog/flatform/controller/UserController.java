package blog.flatform.controller;

import blog.flatform.config.SessionConst;
import blog.flatform.dto.loginDto.SignInDto;
import blog.flatform.dto.loginDto.SignUpDto;
import blog.flatform.entity.User;
import blog.flatform.message.ErrorMessage;
import blog.flatform.message.SuccessfulMessage;
import blog.flatform.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignUpDto signUpDto) {
        userService.signUp(signUpDto);
        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK,"회원 가입 성공"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody SignInDto signInDto, HttpServletRequest request) {
        User loginUser = userService.login(signInDto.getEmail(), signInDto.getPassword());

        if (loginUser == null) {
            return ResponseEntity.
                    status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorMessage(HttpStatus.UNAUTHORIZED,"아이디 또는 비밀번호가 맞지 않습니다."));
        }

        String[] sessionData = {loginUser.getName(), loginUser.getEmail()};

        // 세션이 있으면 세션 반환, 없을 시 세션 신규 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 아이디 보관
        session.setAttribute(SessionConst.LOGIN_USER, sessionData);
        session.setMaxInactiveInterval(1800); // 1800초 -> 30분

        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK,"로그인 성공"));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorMessage(HttpStatus.UNAUTHORIZED, "이미 로그아웃 하셨습니다."));
        }

        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK,"로그아웃 성공"));
    }
}
