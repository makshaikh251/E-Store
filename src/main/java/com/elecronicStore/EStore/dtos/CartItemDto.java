package com.elecronicStore.EStore.dtos;

import com.elecronicStore.EStore.entities.Cart;
import com.elecronicStore.EStore.entities.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private int cartItemId;

    private ProductDto product;

    private int quantity;

    private int totalPrice;

}
