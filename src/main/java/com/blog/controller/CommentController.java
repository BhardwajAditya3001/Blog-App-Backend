package com.blog.controller;

import com.blog.payloads.CommentDto;
import com.blog.services.CommentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{user_id}/post/{post_id}/comment")
    public ResponseEntity<CommentDto> postComment(@RequestBody CommentDto commentDto, @PathVariable Integer post_id,@PathVariable Integer user_id){
        CommentDto newComment = commentService.createComment(commentDto,post_id,user_id);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @PutMapping("/user/{user_id}/post/{post_id}/comment/{comment_id}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto ,
                                                    @PathVariable Integer post_id,
                                                    @PathVariable Integer user_id,
                                                    @PathVariable Integer comment_id){
        CommentDto updateComment = commentService.updateComment(commentDto,post_id,user_id,comment_id);
        return ResponseEntity.ok(updateComment);
    }

    @GetMapping("/user/{user_id}/post/{post_id}/comment/{comment_id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Integer user_id,@PathVariable Integer post_id,@PathVariable Integer comment_id){
        CommentDto comment = commentService.getComment(user_id,post_id,comment_id);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/user/{user_id}/post/{post_id}/comment/{comment_id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer user_id,@PathVariable Integer post_id,@PathVariable Integer comment_id){
        commentService.deleteComment(user_id,post_id,comment_id);
        return ResponseEntity.ok(Map.of("Message","Comment of id " + comment_id + " is deleted"));
    }
}
