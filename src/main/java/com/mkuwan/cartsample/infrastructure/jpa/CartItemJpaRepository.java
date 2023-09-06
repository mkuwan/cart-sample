package com.mkuwan.cartsample.infrastructure.jpa;

import com.mkuwan.cartsample.infrastructure.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, String> {
    Optional<CartItemEntity> findByCartIdAndItemId(String cartId, String itemId);
}
