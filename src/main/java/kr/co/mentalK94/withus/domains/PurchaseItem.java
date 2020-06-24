package kr.co.mentalK94.withus.domains;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItem implements Serializable {

    private Long id; // purchaseItem id

    private Long productId; // 상품 id

    private Long purchaseId; // 구매 id

    private int quantity; // 구매수량

    private Product product; // 구매 상품정보
}
