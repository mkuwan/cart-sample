package com.mkuwan.cartsample.usecase.service;

import com.mkuwan.cartsample.usecase.dto.CartDto;
import com.mkuwan.cartsample.usecase.dto.CartItemDto;

public interface ICartUseCaseService {

    CartDto getCartByCartId(String cartId);

    CartDto putItemIntoCart(String cartId, String userId, CartItemDto cartItemDto);

    CartDto modifyCartItem(String cartId, String userId, CartItemDto cartItemDto);

}
