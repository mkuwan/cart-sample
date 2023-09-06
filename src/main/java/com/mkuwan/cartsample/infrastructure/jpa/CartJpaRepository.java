package com.mkuwan.cartsample.infrastructure.jpa;

import com.mkuwan.cartsample.infrastructure.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, String> {
}
