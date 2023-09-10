package com.elecronicStore.EStore.services;

import com.elecronicStore.EStore.dtos.CreateOrderRequest;
import com.elecronicStore.EStore.dtos.OrderDto;
import com.elecronicStore.EStore.dtos.PageableResponse;

import java.util.List;


public interface OrderService {


    OrderDto createOrder(CreateOrderRequest orderDto);

    void removeOrder(String orderId);

    List<OrderDto> getOrdersOfUser(String userId);

    PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize,String sortBy, String sortDir);
}
