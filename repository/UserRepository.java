package com.example.hotelmanagementapi.repository;

import com.example.hotelmanagementapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    public Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

}
