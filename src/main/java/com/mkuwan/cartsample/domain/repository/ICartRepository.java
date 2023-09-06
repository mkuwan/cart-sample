package com.mkuwan.cartsample.domain.repository;

import com.mkuwan.cartsample.domain.model.CartModel;
import org.springframework.stereotype.Repository;


public interface ICartRepository {
    void save(CartModel cartModel);

    CartModel getCartByCartId(String cartId);

    CartModel getCartByBuyerId(String buyerId);
}
