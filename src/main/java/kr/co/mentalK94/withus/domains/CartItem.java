package kr.co.mentalK94.withus.domains;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable {

    private Long id; // CartItem id

    private Long productId; // 상품 id

    private Long cartId; // cart Id

    private int quantity; // 장바구니에 담긴 상품 수량

    private int totalPrice; // 총 가격

}
