package kr.co.mentalK94.withus.domains;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Long id; // 제품 id

    private String name; // 제품명

    private String category; // 제품 카테고리

    private int price; // 제품 가격

    private String manufacturer; // 제품 제조사

    private int stock; // 제품 재고

    private String description; // 제품 설명
}