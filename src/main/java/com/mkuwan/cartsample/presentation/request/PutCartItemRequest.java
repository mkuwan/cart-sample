package com.mkuwan.cartsample.presentation.request;

import com.mkuwan.cartsample.presentation.viewmodel.CartItemViewModel;
import com.mkuwan.cartsample.usecase.dto.CartItemDto;
import lombok.Data;

@Data
public class PutCartItemRequest {
    private String cartId;
    private String buyerId;
    private String itemId;
    private String itemName;
    private long itemPrice;
    private int itemAmount;
    private int itemLimitedCount;

    public CartItemDto toDto(){
        return new CartItemDto(this.cartId, this.itemId, this.itemName,
                this.itemPrice, this.itemAmount, this.itemLimitedCount);
    }

}
