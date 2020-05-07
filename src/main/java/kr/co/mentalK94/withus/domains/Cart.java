package kr.co.mentalK94.withus.domains;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

    private List<CartItem> cartItems; // CartItem List

    private int grandTotalPrice; // 총 가격
}
