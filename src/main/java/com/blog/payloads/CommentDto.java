package com.blog.payloads;

import com.blog.entities.Post;
import com.blog.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private int id;
    private String content;
    private UserDto user;
}
