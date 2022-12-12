package com.evoke.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evoke.flipkart.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
