package com.anudeep.blog.repositories;

import com.anudeep.blog.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Role,Integer> {
}
