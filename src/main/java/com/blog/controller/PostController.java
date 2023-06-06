package com.blog.controller;

import com.blog.config.AppConstants;
import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{user_id}/category/{category_id}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto , @PathVariable Integer user_id, @PathVariable Integer category_id){
        PostDto newPost = postService.createPost(postDto,user_id,category_id);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/user/{user_id}/category/{category_id}/post/{post_id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer post_id){
        PostDto updated_post = postService.updatePost(postDto,post_id);
        return ResponseEntity.ok(updated_post);
    }

    @GetMapping("/category/{category_id}/posts")
    public ResponseEntity<PostResponse> getAllPostByCategory(@PathVariable Integer category_id ,
                                                              @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                              @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize){
        PostResponse allPosts = postService.getAllPostByCategory(category_id,pageNumber,pageSize);
        return ResponseEntity.ok(allPosts);
    }

    @GetMapping("/user/{user_id}/posts")
    public ResponseEntity<PostResponse> getAllPostByUser(@PathVariable Integer user_id ,
                                                         @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                         @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize
                                                         ){
        PostResponse allPosts = postService.getAllPostByUser(user_id,pageNumber,pageSize);
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy
    ){
        PostResponse allPosts = postService.getAllPost(pageNumber,pageSize,sortBy);
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }

    @GetMapping("/post/{post_id}")
    public ResponseEntity<PostDto> getPostByID(@PathVariable Integer post_id){
        PostDto post = postService.getPost(post_id);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/post/{post_id}")
    public ResponseEntity<?> deletePostById(@PathVariable Integer post_id){
        postService.deletePost(post_id);
        return ResponseEntity.ok(Map.of("message","Post deleted successfully"));
    }
    @GetMapping("/posts/search")
    public ResponseEntity<List<PostDto>> searchPost(@RequestParam("query") String query){
        List<PostDto> searchPosts = postService.searchPost(query);
        return new ResponseEntity<>(searchPosts,HttpStatus.OK);
    }
}
