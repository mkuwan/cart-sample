package com.mkuwan.cartsample.infrastructure.repository;

import com.mkuwan.cartsample.domain.model.CartModel;
import com.mkuwan.cartsample.domain.repository.ICartRepository;
import com.mkuwan.cartsample.domain.valueobject.Buyer;
import com.mkuwan.cartsample.domain.valueobject.CartItem;
import com.mkuwan.cartsample.infrastructure.entity.CartEntity;
import com.mkuwan.cartsample.infrastructure.entity.CartItemEntity;
import com.mkuwan.cartsample.infrastructure.jpa.CartItemJpaRepository;
import com.mkuwan.cartsample.infrastructure.jpa.CartJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CartRepository implements ICartRepository {
    private final CartJpaRepository cartJpaRepository;
    private final CartItemJpaRepository cartItemJpaRepository;

    public CartRepository(CartJpaRepository cartJpaRepository,
                          CartItemJpaRepository cartItemJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
        this.cartItemJpaRepository = cartItemJpaRepository;
    }

    @Override
    public void save(CartModel cartModel) {
        cartModel.CartItems().forEach(x -> {
            var item = fromItemModel(x);
            cartItemJpaRepository.save(item);
        });

        cartJpaRepository.save(fromModel(cartModel));
    }

    @Override
    public CartModel getCartByCartId(String cartId) {
        if(cartId == null)
            return null;
        var cart = cartJpaRepository.findById(cartId);

        if(cart.isPresent())
            return fromEntity(cart.get());
        else
            return null; // new CartModel(null, null, null);
    }

    @Override
    public CartModel getCartByBuyerId(String buyerId) {
        return null;
    }

    private CartEntity fromModel(CartModel cartModel){
        List<CartItemEntity> cartItemEntities = new ArrayList<>();
        cartModel.CartItems().forEach(x -> {
            cartItemEntities.add(fromItemModel(x));
        });
        return new CartEntity(cartModel.CartId(), cartModel.Buyer().buyerId(),
                cartItemEntities);
    }

    private CartItemEntity fromItemModel(CartItem cartItem){
        var existItem = cartItemJpaRepository.findByCartIdAndItemId(cartItem.cartId(), cartItem.itemId());

        if(existItem.isPresent()){
            var item = existItem.get();
            return new CartItemEntity(item.getId(), item.getCartId(), item.getItemId(),
                    item.getItemName(),
                    cartItem.expectedPrice(),
                    cartItem.expectedAmount(), cartItem.itemLimitedCount());
        } else {
            return new CartItemEntity(UUID.randomUUID().toString(), cartItem.cartId(), cartItem.itemId(),
                    cartItem.itemName(),
                    cartItem.expectedPrice(),
                    cartItem.expectedAmount(), cartItem.itemLimitedCount());
        }
    }

    private CartModel fromEntity(CartEntity cartEntity){
        var cartItems = cartEntity.getCartItemEntities();
        List<CartItem> cartItemList = new ArrayList<>();

        cartItems.forEach(x -> {
            cartItemList.add(fromItemEntity(x));
        });

        return new CartModel(cartEntity.getCartId(),
                new Buyer(cartEntity.getBuyerId()),
                cartItemList);
    }

    private CartItem fromItemEntity(CartItemEntity cartItemEntity){
        return new CartItem(cartItemEntity.getCartId(),
                cartItemEntity.getItemId(),
                cartItemEntity.getItemName(),
                cartItemEntity.getItemPrice(),
                cartItemEntity.getItemAmount(),cartItemEntity.getItemLimitedCount());
    }

}
