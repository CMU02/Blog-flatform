package blog.flatform.service.Impl;

import blog.flatform.dto.commentDto.CommentRequestDto;
import blog.flatform.dto.commentDto.CommentResponseDto;
import blog.flatform.dto.commentDto.CommentUpdateRequestDto;
import blog.flatform.dto.loginDto.SessionUserDto;
import blog.flatform.entity.Comment;
import blog.flatform.repository.CommentRepository;
import blog.flatform.repository.PostRepository;
import blog.flatform.repository.UserRepository;
import blog.flatform.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        // 해당 게시글 존재 여부
        postRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Dto -> Entity Convert
        Comment comment = commentRequestDto.toEntity(commentRequestDto);
        // Repository save
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }

    @Override
    @Transactional
    public void updateComment(CommentUpdateRequestDto commentUpdateRequestDto) {
        // 해당 게시글 존재 여부
        postRepository.findById(commentUpdateRequestDto.getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        // 해당 댓글 존재 여부
        Comment findComment = commentRepository.findById(commentUpdateRequestDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // 변경 감지
        findComment.updateComment(commentUpdateRequestDto.getContent());
    }

    @Override
    public void deleteComment(Long id, String email) {
        Comment findComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        commentRepository.delete(findComment);
    }
}
