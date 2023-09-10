package com.elecronicStore.EStore.repositories;

import com.elecronicStore.EStore.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>
{
}
