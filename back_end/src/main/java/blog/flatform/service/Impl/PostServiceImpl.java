package blog.flatform.service.Impl;

import blog.flatform.dto.postDto.savePostDto;
import blog.flatform.dto.postDto.updatePostDto;
import blog.flatform.entity.Category;
import blog.flatform.entity.Post;
import blog.flatform.entity.User;
import blog.flatform.repository.PostRepository;
import blog.flatform.service.PostService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final EntityManager em;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 글이 없습니다 ID : " + id)
        );
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

        Post save = postRepository.save(savePost);
        return save;
    }

    @Override
    @Transactional
    public void  updatePost(Long id, updatePostDto updatePostDto) {
        Post findPost = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 글이 없습니다 ID : " + id));

        // 변경 감지
        findPost.updatePost(
                updatePostDto.getTitle(),
                updatePostDto.getContent(),
                updatePostDto.getCategory()
        );
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        Post findPost = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 글이 없습니다 PostID : " + id)
        );

        postRepository.delete(findPost);
    }
}
