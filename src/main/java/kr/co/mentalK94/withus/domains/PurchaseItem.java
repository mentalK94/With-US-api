package kr.co.mentalK94.withus.domains;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItem {

    private Long id; // purchaseItem id

    private Long productId; // 상품 id

    private Long purchaseId; // 구매 id

    private int quantity; // 구매수량
}
