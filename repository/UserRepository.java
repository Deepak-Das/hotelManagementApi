package com.example.hotelmanagementapi.repository;

import com.example.hotelmanagementapi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    boolean existsByEmail(String email);
    public Optional<UserEntity> findByEmail(String email);
}
