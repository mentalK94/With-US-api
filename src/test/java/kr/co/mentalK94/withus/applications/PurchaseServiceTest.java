package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Purchase;
import kr.co.mentalK94.withus.mappers.ProductMapper;
import kr.co.mentalK94.withus.mappers.PurchaseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class PurchaseServiceTest {

    private PurchaseService purchaseService;

    @Mock
    private PurchaseMapper purchaseMapper;

    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        purchaseService = new PurchaseService(purchaseMapper, productMapper);
    }


    @Test
    public void addPurchaseTest() {
        Purchase purchase = Purchase.builder().payAmount(9000)
                .totalPrice(10000)
                .usingPoint(1000)
                .shippingAddress("삼덕로 63번길 32, 101동 1001호")
                .shippingMemo("부재시 집 앞에 놔주세요.")
                .userId(3L)
                .build();

        purchaseService.addPurchase(purchase);

        verify(purchaseMapper).insertPurchase(any());
    }

}