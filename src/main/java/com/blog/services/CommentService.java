package com.blog.services;

import com.blog.entities.Comment;
import com.blog.payloads.CommentDto;
import com.blog.payloads.PostDto;
import org.springframework.stereotype.Service;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
    CommentDto updateComment(CommentDto commentDto,Integer posId,Integer userId,Integer commentId);
    CommentDto getComment(Integer userID,Integer postId,Integer commentId);
    void deleteComment(Integer userId,Integer postId,Integer commentId);
}
