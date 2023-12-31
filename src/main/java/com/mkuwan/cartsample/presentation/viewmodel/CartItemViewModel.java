package com.mkuwan.cartsample.presentation.viewmodel;

import com.mkuwan.cartsample.domain.valueobject.CartItem;
import com.mkuwan.cartsample.usecase.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.NumberFormat;

@AllArgsConstructor
@Data
public class CartItemViewModel {

    private String cartId;
    private String itemId;
    private String itemName;
    private long itemPrice;
    private String formattedItemPrice;
    private int itemAmount;
    private String itemAmountView;
    private int itemLimitedCount;
    private String limitedCountView;

    public CartItemDto toDto(){
        return new CartItemDto(this.cartId, this.itemId, this.itemName,
                this.itemPrice, this.itemAmount, this.itemLimitedCount);
    }

    public static CartItemViewModel fromDto(CartItemDto itemDto){
        var numberFormat = NumberFormat.getNumberInstance();
        return new CartItemViewModel(itemDto.getCartId(), itemDto.getItemId(), itemDto.getItemName(),
                itemDto.getItemPrice(),
                numberFormat.format(itemDto.getItemPrice()) + "円",
                itemDto.getItemAmount(),
                itemDto.getItemAmount() + "個",
                itemDto.getItemLimitedCount(),
                "購入できるのは" + itemDto.getItemLimitedCount() + "個までです");
    }
}
