package com.anudeep.blog.repositories;

import com.anudeep.blog.entites.Category;
import com.anudeep.blog.entites.Post;
import com.anudeep.blog.entites.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(user user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
