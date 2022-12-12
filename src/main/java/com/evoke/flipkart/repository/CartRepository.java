package com.evoke.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evoke.flipkart.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

}
