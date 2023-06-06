package com.blog.services;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto post,Integer user_id,Integer category_id);
    PostDto updatePost(PostDto post, Integer post_id);
    PostDto getPost(Integer post_id);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy);
    void deletePost(Integer post_id);
    List<PostDto> searchPost(String keyword);

    PostResponse getAllPostByCategory(Integer category_id , Integer pageNumber , Integer pageSize);

    PostResponse getAllPostByUser(Integer user_id,Integer pageNumber,Integer pageSize);

}
