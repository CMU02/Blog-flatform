package blog.flatform.service.Impl;

import blog.flatform.dto.postDto.savePostDto;
import blog.flatform.dto.postDto.updatePostDto;
import blog.flatform.entity.Post;
import blog.flatform.repository.PostRepository;
import blog.flatform.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Post savePost(savePostDto savePostDto) {
        Post savePost = Post.builder()
                .title(savePostDto.getTitle())
                .content(savePostDto.getContent())
                .user(savePostDto.getUser())
                .category(savePostDto.getCategory())
                .build();

        return postRepository.save(savePost);
    }

    @Override
    @Transactional
    public Post updatePost(updatePostDto updatePostDto) {
        Post findPost = postRepository.findById(updatePostDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 아이디가 없습니다 ID : " + updatePostDto.getId())
        );

        findPost.builder()
                .title(updatePostDto.getTitle())
                .content(updatePostDto.getContent())
                .user(updatePostDto.getUser())
                .category(updatePostDto.getCategory() != null ? updatePostDto.getCategory() : null)
                .build();

        return postRepository.save(findPost);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        Post findPost = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 글은 없습니다 PostID : " + id)
        );

        postRepository.delete(findPost);
    }
}
