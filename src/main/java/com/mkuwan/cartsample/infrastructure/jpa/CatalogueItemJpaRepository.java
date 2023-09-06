package com.mkuwan.cartsample.infrastructure.jpa;

import com.mkuwan.cartsample.infrastructure.entity.CatalogueItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueItemJpaRepository extends JpaRepository<CatalogueItemEntity, String> {
}
