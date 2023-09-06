package com.mkuwan.cartsample.usecase.service;

import com.mkuwan.cartsample.domain.model.CartModel;
import com.mkuwan.cartsample.domain.repository.ICartRepository;
import com.mkuwan.cartsample.domain.valueobject.Buyer;
import com.mkuwan.cartsample.domain.valueobject.CartItem;
import com.mkuwan.cartsample.usecase.dto.CartDto;
import com.mkuwan.cartsample.usecase.dto.CartItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class CartUseCaseService implements ICartUseCaseService {
    private final ICartRepository cartRepository;

    public CartUseCaseService(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDto getCartByCartId(String cartId) {
        return null;
    }

    @Override
    public CartDto putItemIntoCart(String cartId, String userId, CartItemDto cartItemDto) {
        // 追加する商品をドメインモデルに変換
        CartItem item = new CartItem(cartId, cartItemDto.getItemId(), cartItemDto.getItemName(),
                cartItemDto.getItemPrice(), cartItemDto.getItemAmount(),
                cartItemDto.getItemLimitedCount());

        // カートのデータ取得
        var cart = cartRepository.getCartByCartId(cartId);

        // カートがない場合は新規作成する
        if(cart == null){
            List<CartItem> cartItems = new ArrayList<>(List.of(item));
            cart = new CartModel(null,
                    new Buyer(null),
                    cartItems);
        } else {
            // カートに商品追加する このようにドメインモデルのメソッドを使用すること
            cart.putItemIntoCart(item);

            // 以下の書き方はダメ
            // cart.CartItems().add(item);
        }

        // カートを保存する
        cartRepository.save(cart);

        // カートを返す
        return CartDto.fromCartModel(cart);
    }

    @Override
    public CartDto modifyCartItem(String cartId, String userId, CartItemDto cartItemDto) {
        // 変更する商品をドメインモデルに変換
        CartItem item = new CartItem(cartId, cartItemDto.getItemId(), cartItemDto.getItemName(),
                cartItemDto.getItemPrice(), cartItemDto.getItemAmount(),
                cartItemDto.getItemLimitedCount());

        // カートのデータ取得
        var cart = cartRepository.getCartByCartId(cartId);

        // カートがないというのはあり得ないのでErrorとする
        if(cart == null){
            throw new IllegalArgumentException("カートが作成されていません");
        } else {
            // カートの商品を更新する
            cart.modifyItemInCart(item);
        }

        // カートを保存する
        cartRepository.save(cart);

        // カートを返す
        return CartDto.fromCartModel(cart);
    }
}
