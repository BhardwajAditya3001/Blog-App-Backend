package com.blog.services.impl;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto,Integer user_id,Integer category_id) {

        User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotException("User","id",user_id));
        Category category = this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotException("Category","Id",category_id));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post new_post = postRepo.save(post);
        return modelMapper.map(new_post,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer post_id) {
        Post post = postRepo.findById(post_id).orElseThrow(()->new ResourceNotException("Post","id",post_id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setAddDate(new Date());
        post.setImageName("default.png");
        post.setUser(post.getUser());
        post.setCategory(post.getCategory());
        Post updated_post = postRepo.save(post);
        return modelMapper.map(updated_post,PostDto.class);
    }

    @Override
    public PostDto getPost(Integer post_id) {
        Post post = postRepo.findById(post_id).orElseThrow(()->new ResourceNotException("Post","Id",post_id));
        PostDto postDto = modelMapper.map(post,PostDto.class);
        return postDto;
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy) {
        Pageable p = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());
        Page<Post> pagePost = postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> allPostsDto = allPosts.stream().map(post -> modelMapper.map(post,PostDto.class)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostsDto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getNumberOfElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public void deletePost(Integer post_id) {
        postRepo.deleteById(post_id);
    }


    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> searchPost = postRepo.searchPost(keyword);
        List<PostDto> searchPostDto = searchPost.stream().map(post -> modelMapper.map(post,PostDto.class)).toList();
        return searchPostDto;
    }

    @Override
    public PostResponse getAllPostByCategory(Integer category_id,Integer pageNumber , Integer pageSize) {
        Category category = categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotException("Category","Id",category_id));

        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost = postRepo.findByCategory(category,p);
        List<Post> allPosts = pagePost.getContent();

        List<PostDto> allPostsDto = allPosts.stream().map(post->modelMapper.map(post,PostDto.class)).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostsDto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getNumberOfElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getAllPostByUser(Integer user_id,Integer pageNumber,Integer pageSize) {
        User user = userRepo.findById(user_id).orElseThrow(()->new ResourceNotException("User","Id",user_id));

        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost = postRepo.findByUser(user,p);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> allPostsDto = allPosts.stream().map(post->modelMapper.map(post, PostDto.class)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostsDto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getNumberOfElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }
}
