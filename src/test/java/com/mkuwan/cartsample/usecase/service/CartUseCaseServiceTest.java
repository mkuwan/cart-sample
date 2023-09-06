package com.mkuwan.cartsample.usecase.service;

import com.mkuwan.cartsample.infrastructure.entity.CatalogueItemEntity;
import com.mkuwan.cartsample.infrastructure.jpa.CatalogueItemJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartUseCaseServiceTest {
    @Autowired
    private CatalogueItemJpaRepository catalogueItemJpaRepository;

    @BeforeEach
    void setup(){
        // サンプルデータ作成
        for (int i = 0; i < 20; i++) {
            CatalogueItemEntity catalogueItem = new CatalogueItemEntity("item-id-" + (i + 1),
                    "商品" + (i + 1), 100 * (i + 1), i + 1, 5);
            catalogueItemJpaRepository.save(catalogueItem);
        }
        // 作成されるカタログデータ
        // PURCHASE_LIMIT  	STOCK_AMOUNT  	SALES_PRICE  	CATALOG_ITEM_ID  	CATALOG_ITEM_NAME
        // 5                	1	            100	            item-id-1       	商品1
        // 5                	2	            200	            item-id-2       	商品2
        // 5                  	3	            300	            item-id-3       	商品3
        // 5                	4	            400	            item-id-4       	商品4
        // 5                	5	            500	            item-id-5       	商品5
        // 5                	6	            600	            item-id-6       	商品6
        // 5                	7	            700	            item-id-7       	商品7
        // 5                	8	            800	            item-id-8       	商品8
        // 5                	9	            900	            item-id-9       	商品9
        // 5                   10	           1000	           item-id-10       	商品10
        // 5	               11	           1100	           item-id-11       	商品11
        // 5	               12	           1200	           item-id-12       	商品12
        // 5	               13	           1300	           item-id-13       	商品13
        // 5	               14	           1400	           item-id-14       	商品14
        // 5	               15	           1500	           item-id-15       	商品15
        // 5	               16	           1600	           item-id-16       	商品16
        // 5	               17	           1700	           item-id-17       	商品17
        // 5	               18	           1800	           item-id-18       	商品18
        // 5	               19	           1900	           item-id-19       	商品19
        // 5	               20	           2000	           item-id-20       	商品20
    }

    @AfterEach
    void tearDown(){
        for (int i = 0; i < 20; i++) {
            String catalogueId = "item-id-" + (i + 1);
            catalogueItemJpaRepository.deleteById(catalogueId);
        }
    }

}