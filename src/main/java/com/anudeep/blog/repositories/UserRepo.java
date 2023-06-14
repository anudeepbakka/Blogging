package com.anudeep.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anudeep.blog.entites.user;

import java.util.Optional;

public interface UserRepo extends JpaRepository<user , Integer> {

    Optional<user> findByEmail(String email);
}
