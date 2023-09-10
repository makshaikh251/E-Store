package com.elecronicStore.EStore.controllers;

import com.elecronicStore.EStore.dtos.AddItemToCartRequest;
import com.elecronicStore.EStore.dtos.ApiResponseMessage;
import com.elecronicStore.EStore.dtos.CartDto;
import com.elecronicStore.EStore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCart(
            @PathVariable String userId,
            @RequestBody AddItemToCartRequest request){

        CartDto cartDto = cartService.addItemToCart(userId, request);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponseMessage> removeItemFromCart(
            @PathVariable int itemId,
            @PathVariable String userId
    ){
        cartService.removeItemFromCart(userId,itemId);
        ApiResponseMessage response = ApiResponseMessage.builder().message("Item is removed").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> clearCart(@PathVariable String userId
    ){
        cartService.clearCart(userId);
        ApiResponseMessage response = ApiResponseMessage.builder().message("Cart is empty").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId){

        CartDto cartDto = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

}
