package blog.flatform.controller;

import blog.flatform.config.SessionConst;
import blog.flatform.dto.loginDto.SessionUserDto;
import blog.flatform.dto.postDto.*;
import blog.flatform.entity.Post;
import blog.flatform.message.ErrorMessage;
import blog.flatform.message.SuccessfulMessage;
import blog.flatform.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostControllerV1 {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findPostById(@PathVariable("id") Long id) {
        Post findPost = postService.getPostById(id);
        return ResponseEntity.ok(new findPostDto(findPost));
    }

    @GetMapping("/list")
    public ResponseEntity<AllPostAndCountDto<?>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<findAllPostDto> collect = posts.stream()
                .map(findAllPostDto::new)
                .toList();
        return ResponseEntity.ok(new AllPostAndCountDto<>(collect.size(), collect));
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> savePost(@RequestBody savePostDto savePostDto, HttpSession session) {
        SessionUserDto sessionUser = (SessionUserDto) session.getAttribute(SessionConst.LOGIN_USER);
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorMessage(HttpStatus.UNAUTHORIZED, "로그인이 필요한 기능입니다."));
        } else {
            String email = savePostDto.getUser().getEmail();
            String name = savePostDto.getUser().getName();
            if (!sessionUser.getUserEmail().equals(email) || !sessionUser.getUsername().equals(name)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ErrorMessage(HttpStatus.FORBIDDEN, "잘못된 유저 정보입니다."));
            }
        }

        postService.savePost(savePostDto);
        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK, "성공적으로 저장하였습니다."));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updatePost(@PathVariable("id") Long id,
                                             @RequestBody updatePostDto updatePostDto, HttpSession session) {
        SessionUserDto sessionUser = (SessionUserDto) session.getAttribute(SessionConst.LOGIN_USER);
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorMessage(HttpStatus.UNAUTHORIZED, "로그인이 필요한 기능입니다."));
        } else {
            String email = updatePostDto.getUser().getEmail();
            String name = updatePostDto.getUser().getName();
            if (!sessionUser.getUserEmail().equals(email) || !sessionUser.getUsername().equals(name)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ErrorMessage(HttpStatus.FORBIDDEN, "잘못된 유저 정보입니다."));
            }
        }

        postService.updatePost(id ,updatePostDto);
        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK, "성공적으로 변경되었습니다."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK, "성공적으로 삭제했습니다."));
    }
}
