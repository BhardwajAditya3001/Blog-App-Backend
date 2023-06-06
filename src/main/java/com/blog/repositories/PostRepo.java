package com.blog.repositories;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.payloads.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

    Page<Post> findByUser(User user , Pageable p);
    Page<Post> findByCategory(Category category , Pageable p);

    @Query(value = "SELECT * FROM post p WHERE p.title LIKE CONCAT('%',:query,'%')",nativeQuery = true)
    List<Post> searchPost(String query);
}
