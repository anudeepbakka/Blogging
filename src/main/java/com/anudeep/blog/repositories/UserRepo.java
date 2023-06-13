package com.anudeep.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anudeep.blog.entites.user;

public interface UserRepo extends JpaRepository<user , Integer> {

}
