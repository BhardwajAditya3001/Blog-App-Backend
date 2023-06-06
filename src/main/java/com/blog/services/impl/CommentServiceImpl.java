package com.blog.services.impl;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotException;
import com.blog.payloads.CommentDto;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.payloads.UserDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.CommentService;
import com.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId ,Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotException("User","Id",userId));
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotException("Post","Id",postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setUser(user);
        comment.setPost(post);

        Comment newComment = commentRepo.save(comment);
        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer postId,Integer userId, Integer commentId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotException("User","Id",userId));
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotException("Post","Id",postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotException("Comment","Id",commentId));
        comment.setContent(commentDto.getContent());
        Comment updatedComment = commentRepo.save(comment);
        return modelMapper.map(updatedComment,CommentDto.class);
    }

    @Override
    public CommentDto getComment(Integer userId,Integer postId, Integer commentId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotException("User","Id",userId));
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotException("Post","Id",postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotException("Comment","Id",commentId));
        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer userId,Integer postId, Integer commentId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotException("User","Id",userId));
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotException("Post","Id",postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotException("Comment","Id",commentId));
        userRepo.deleteById(commentId);
    }
}
