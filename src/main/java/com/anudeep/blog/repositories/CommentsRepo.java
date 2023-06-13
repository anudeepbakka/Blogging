package com.anudeep.blog.repositories;

import com.anudeep.blog.entites.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comments,Integer> {
}
