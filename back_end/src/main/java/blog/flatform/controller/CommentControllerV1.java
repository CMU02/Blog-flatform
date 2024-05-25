package blog.flatform.controller;

import blog.flatform.config.SessionConst;
import blog.flatform.dto.commentDto.*;
import blog.flatform.dto.loginDto.SessionUserDto;
import blog.flatform.entity.Comment;
import blog.flatform.entity.User;
import blog.flatform.message.ErrorMessage;
import blog.flatform.message.SuccessfulMessage;
import blog.flatform.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentControllerV1 {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getAllComments() {
        List<Comment> allComments = commentService.getAllComments();
        List<ListCommentsDto> collect = allComments.stream()
                .map(ListCommentsDto::new)
                .toList();

        return ResponseEntity.ok(new CommentsAndCountDto<>(collect.size(), collect));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDto commentRequestDto, HttpSession session) {
        SessionUserDto sessionUser = (SessionUserDto) session.getAttribute(SessionConst.LOGIN_USER);
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorMessage(HttpStatus.UNAUTHORIZED, "로그인이 필요한 기능입니다."));
        } else {
            String email = commentRequestDto.getUser().getEmail();
            String name = commentRequestDto.getUser().getName();
            if (!sessionUser.getUserEmail().equals(email) || !sessionUser.getUsername().equals(name)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ErrorMessage(HttpStatus.FORBIDDEN, "잘못된 유저 정보입니다."));
            }
        }

        CommentResponseDto responseComment = commentService.createComment(commentRequestDto);
        return ResponseEntity.ok(responseComment);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequestDto dto) {
        commentService.updateComment(id ,dto);
        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK, "성공적으로 수정되었습니다."));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id, HttpSession session) {
        SessionUserDto sessionUser = (SessionUserDto) session.getAttribute(SessionConst.LOGIN_USER);
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorMessage(HttpStatus.UNAUTHORIZED, "로그인이 필요한 계정입니다."));
        }
        commentService.deleteComment(id, sessionUser.getUserEmail());
        return ResponseEntity.ok(new SuccessfulMessage(HttpStatus.OK, "성공적으로 삭제되었습니다."));
    }
}
