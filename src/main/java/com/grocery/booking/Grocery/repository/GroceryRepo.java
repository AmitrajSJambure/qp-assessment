package com.grocery.booking.Grocery.repository;

import com.grocery.booking.Grocery.dao.entity.GroceryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryRepo extends JpaRepository<GroceryEntity, Long> {
    GroceryEntity findByName(String name);
}
