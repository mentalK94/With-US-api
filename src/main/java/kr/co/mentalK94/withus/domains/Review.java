package kr.co.mentalK94.withus.domains;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    private Long id; // review id

    private Long productId; // product id

    private Long userId; // user id

    private String writer; // 작성자

    private double rating; // 평점

    private String description; // 리뷰 내용

    private String creationDate; // 작성일

    private String photo1; // 상품 리뷰 사진 1

    private String photo2; // 상품 리뷰 사진 2

    private String photo3; // 상품 리뷰 사진 3

}
