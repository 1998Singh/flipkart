package com.evoke.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evoke.flipkart.entity.ItemPriceEntity;

@Repository
public interface ItemPriceRepository extends JpaRepository<ItemPriceEntity, Long> {

}