package com.elecronicStore.EStore.services;

import com.elecronicStore.EStore.dtos.AddItemToCartRequest;
import com.elecronicStore.EStore.dtos.CartDto;

public interface CartService {

    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    void removeItemFromCart(String userId, int cartItem);

    void clearCart(String userId);

    CartDto getCartByUser(String userId);
}
