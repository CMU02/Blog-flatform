package blog.flatform.service;

import blog.flatform.dto.SignInDto;
import blog.flatform.dto.SignUpDto;
import blog.flatform.entity.User;

import java.util.List;

public interface UserService {
    Long signUp(SignUpDto signUpDto);
    User login(String email, String password);
    List<User> findUsers();
}
