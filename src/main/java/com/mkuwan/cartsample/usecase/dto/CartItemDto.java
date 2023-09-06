package com.mkuwan.cartsample.usecase.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class CartItemDto {
    private String cartId;
    private String itemId;
    private String itemName;
    private long itemPrice;
    private int itemAmount;
    private int itemLimitedCount;
}
