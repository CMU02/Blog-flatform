package blog.flatform.controller;

import blog.flatform.config.SessionConst;
import blog.flatform.dto.postDto.*;
import blog.flatform.entity.Post;
import blog.flatform.entity.User;
import blog.flatform.message.ErrorMessage;
import blog.flatform.message.SuccessfulMessage;
import blog.flatform.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findPostById(@PathVariable("id") Long id) {
        Post findPost = postService.getPostById(id);
        return ResponseEntity.ok(new findPostDto(findPost));
    }

    @GetMapping("/list")
    public ResponseEntity<AllPostDto> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<findAllPostDto> collect = posts.stream()
                .map(findAllPostDto::new)
                .toList();

        for (findAllPostDto findAllPostDto : collect) {
            System.out.println("findAllPostDto.getId() = " + findAllPostDto.getId());
            System.out.println("findAllPostDto.getTitle() = " + findAllPostDto.getTitle());
            System.out.println("findAllPostDto.getContent() = " + findAllPostDto.getContent());
            System.out.println("findAllPostDto.getUsername() = " + findAllPostDto.getUsername());
            System.out.println("findAllPostDto = " + findAllPostDto.getCategoryName());
        }

        return ResponseEntity.ok(new AllPostDto(collect.size(), collect));
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> savePost(@RequestBody savePostDto savePostDto,
                                           HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorMessage(HttpStatus.UNAUTHORIZED, "로그인이 필요한 기능입니다."));
        }
        session.getAttribute(SessionConst.LOGIN_USER);

        postService.savePost(savePostDto);

        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK, "성공적으로 저장하였습니다."));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updatePost(@PathVariable("id") Long id,
                                             @RequestBody updatePostDto updatePostDto,
                                             HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorMessage(HttpStatus.UNAUTHORIZED, "로그인이 필요한 기능입니다."));
        }

        postService.updatePost(id ,updatePostDto);

        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK, "성공적으로 변경되었습니다."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorMessage(HttpStatus.UNAUTHORIZED, "로그인이 필요한 기능입니다."));
        }
        postService.deletePost(id);

        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK, "성공적으로 삭제했습니다."));
    }
}
