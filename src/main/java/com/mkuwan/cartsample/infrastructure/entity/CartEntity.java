package com.mkuwan.cartsample.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Cart")
public class CartEntity {
    @Id
    private String cartId;

    private String buyerId;

    @OneToMany
    private List<CartItemEntity> cartItemEntities;


}
