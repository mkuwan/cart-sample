package com.mkuwan.cartsample.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class CatalogueItemEntity {
    @Id
    private String catalogItemId;
    private String catalogItemName;
    private long salesPrice;
    private int stockAmount;
    private int purchaseLimit;
}
