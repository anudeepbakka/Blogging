package com.anudeep.blog.repositories;
import com.anudeep.blog.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
