package kr.co.mentalK94.withus.domains;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id; // 상품 id

    private String name; // 상품명

    private String category; // 상품 카테고리

    private int price; // 상품 가격

    private String manufacturer; // 상품 제조사

    private int stock; // 상품 재고

    private String description; // 상품 설명

    private MultipartFile productImage; // 상품 이미지

    private String imageFileName; // 상품 이미지 명

    private double rating; // 상품 평점

    private int reviewCount; // 상품 평점 수

    private int limitedQuantity; // 0개 : 제한없음 / 1개이상 : 제한
}
