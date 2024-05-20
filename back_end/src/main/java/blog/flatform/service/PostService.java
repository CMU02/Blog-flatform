package blog.flatform.service;

import blog.flatform.dto.postDto.savePostDto;
import blog.flatform.dto.postDto.updatePostDto;
import blog.flatform.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post savePost(savePostDto savePostDto);
    Post updatePost(updatePostDto updatePostDto);
    void deletePost(Long id);
}
