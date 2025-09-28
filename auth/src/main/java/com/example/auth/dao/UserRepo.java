package com.example.auth.dao;

import com.example.auth.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<CustomUser, Integer> {

    CustomUser findByUsername(String username);

    boolean existsByUsername(String username);
}
