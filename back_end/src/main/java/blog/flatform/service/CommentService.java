package blog.flatform.service;

import blog.flatform.dto.commentDto.CommentRequestDto;
import blog.flatform.dto.commentDto.CommentResponseDto;
import blog.flatform.dto.commentDto.CommentUpdateRequestDto;
import blog.flatform.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    CommentResponseDto createComment(CommentRequestDto commentRequestDto);
    void updateComment(Long id ,CommentUpdateRequestDto commentUpdateRequestDto);
    void deleteComment(Long id, String email);
}
