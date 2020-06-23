package kr.co.mentalK94.withus.domains;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase implements Serializable {

    private Long id; // Purchase id

    private Long userId; // 구매자 id

    private int totalPrice; // 총 상품금액

    private int usingPoint; // 사용한 포인트

    private int payAmount; // 총 결제금액

    private String shippingAddress; // 배송지 주소

    private String shippingMemo; // 배송 메모

    private String phone; // 구매자 연락처

    private String dateTime; // 구매일자

    private String cancelDateTime; // 구매 취소일자

    private int isComplete; // 구매 완료여부

    private ArrayList<PurchaseItem> purchaseItems; // 구매한 상품목록
}
