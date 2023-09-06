package com.mkuwan.cartsample.presentation.viewmodel;

import com.mkuwan.cartsample.usecase.dto.CartDto;
import com.mkuwan.cartsample.usecase.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class CartViewModel {
    private String cartId;
    private String buyerId;
    private List<CartItemViewModel> cartItemDtoList;

    private String message;

    public CartDto toDto(){
        List<CartItemDto> itemDtoList = new ArrayList<>();
        this.cartItemDtoList.forEach(x -> {
            itemDtoList.add(new CartItemDto(this.cartId, x.getItemId(), x.getItemName(),
                    x.getItemPrice(), x.getItemAmount(), x.getItemLimitedCount()));
        });
        return new CartDto(this.cartId, this.buyerId, itemDtoList);
    }

    public static CartViewModel fromDto(CartDto cartDto){
        List<CartItemViewModel> itemViewModelList = new ArrayList<>();
        cartDto.getCartItemDtoList().forEach(x -> {
            itemViewModelList.add(CartItemViewModel.fromDto(x));
        });

        return new CartViewModel(cartDto.getCartId(), cartDto.getBuyerId(),itemViewModelList, null);
    }
}
