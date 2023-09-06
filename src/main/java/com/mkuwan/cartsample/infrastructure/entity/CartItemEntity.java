package com.mkuwan.cartsample.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Entity(name = "CartItem")
public class CartItemEntity {

    @Id
    private String id;

    private String cartId;
    private String itemId;

    private String itemName;
    private long itemPrice;
    private int itemAmount;
    private int itemLimitedCount;


}
