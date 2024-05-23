package blog.flatform.service;

import blog.flatform.dto.postDto.savePostDto;
import blog.flatform.dto.postDto.updatePostDto;
import blog.flatform.entity.Category;
import blog.flatform.entity.Post;
import blog.flatform.entity.User;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post savePost(savePostDto savePostDto);
    void updatePost(Long id, updatePostDto updatePostDto);
    void deletePost(Long id);
}
