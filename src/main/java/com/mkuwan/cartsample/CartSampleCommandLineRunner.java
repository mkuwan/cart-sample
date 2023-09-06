package com.mkuwan.cartsample;

import com.mkuwan.cartsample.infrastructure.entity.CatalogueItemEntity;
import com.mkuwan.cartsample.infrastructure.jpa.CatalogueItemJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CartSampleCommandLineRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CartSampleCommandLineRunner.class);

    private final CatalogueItemJpaRepository catalogueItemJpaRepository;

    public CartSampleCommandLineRunner(CatalogueItemJpaRepository catalogueItemJpaRepository) {
        this.catalogueItemJpaRepository = catalogueItemJpaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // サンプルデータ作成
        for (int i = 0; i < 20; i++) {
            CatalogueItemEntity catalogueItem = new CatalogueItemEntity("item-id-" + (i + 1),
                    "商品" + (i + 1), 100 * (i + 1), i + 1, 5);
            catalogueItemJpaRepository.save(catalogueItem);
        }

    }
}
