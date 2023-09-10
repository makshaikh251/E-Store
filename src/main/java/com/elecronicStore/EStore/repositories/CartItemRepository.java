package com.elecronicStore.EStore.repositories;

import com.elecronicStore.EStore.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

}
