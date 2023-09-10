package com.elecronicStore.EStore.repositories;

import com.elecronicStore.EStore.entities.Cart;
import com.elecronicStore.EStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,String> {

    Optional<Cart> findByUser(User user);
}
