package com.elecronicStore.EStore.services.impl;


import com.elecronicStore.EStore.dtos.AddItemToCartRequest;
import com.elecronicStore.EStore.dtos.CartDto;
import com.elecronicStore.EStore.entities.Cart;
import com.elecronicStore.EStore.entities.CartItem;
import com.elecronicStore.EStore.entities.Product;
import com.elecronicStore.EStore.entities.User;
import com.elecronicStore.EStore.exceptions.BadApiRequestException;
import com.elecronicStore.EStore.exceptions.ResourceNotFoundException;
import com.elecronicStore.EStore.repositories.CartItemRepository;
import com.elecronicStore.EStore.repositories.CartRepository;
import com.elecronicStore.EStore.repositories.ProductRepository;
import com.elecronicStore.EStore.repositories.UserRepository;
import com.elecronicStore.EStore.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartrepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {

        int quantity = request.getQuantity();
        String productId = request.getProductId();

        if (quantity<=0){
            throw new BadApiRequestException("Requested quantity is not valid");
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with given id not found !!"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found !!"));
        Cart cart = null;

        try{
            cart = cartrepository.findByUser(user).get();

        }catch (NoSuchElementException e){
            cart = new Cart();
            cart.setCreatedAt(new Date());
            cart.setCartId(UUID.randomUUID().toString());
        }
        AtomicReference<Boolean> updated =new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
        items = items.stream().map(item -> {

            if (item.getProduct().getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                item.setTotalPrice(item.getTotalPrice()+ (quantity * product.getDiscountedPrice()));
                updated.set(true);
            }
            return item;
        }).collect(Collectors.toList());

       //cart.setItems(updatedItems);
       if(!updated.get()){
           CartItem cartItem = CartItem.builder().quantity(quantity)
                   .totalPrice(quantity * product.getDiscountedPrice())
                   .cart(cart)
                   .product(product).build();

           cart.getItems().add(cartItem);
       }
        cart.setUser(user);
        Cart updatedCart = cartrepository.save(cart);

        return mapper.map(updatedCart,CartDto.class);
    }

    @Override
    public void removeItemFromCart(String userId, int cartItem) {


        CartItem cartItem1 = cartItemRepository.findById(cartItem).orElseThrow(() -> new ResourceNotFoundException("cart item not found !!"));
        cartItemRepository.delete(cartItem1);

    }

    @Override
    public void clearCart(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found !!"));
        Cart cart = cartrepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart of given user not found"));
        cart.getItems().clear();
        cartrepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found !!"));
        Cart cart = cartrepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart of given user not found"));

        return mapper.map(cart,CartDto.class);
    }
}
