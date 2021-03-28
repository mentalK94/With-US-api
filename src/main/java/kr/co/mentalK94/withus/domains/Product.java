package kr.co.mentalK94.withus.domains;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /***
     * 상품 기본키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
     * 상품명
     */
    private String name;

    /***
     * 상품 가격
     */
    private int price;

    /***
     * 상품 재고수량
     */
    private int quantity;

    /***
     * 상품 기본키
     */
    private int limitedQuantity; // 0개 : 제한없음 / 1개이상 : 제한

    /***
     * 상품 설명
     */
    private String description;

    /***
     * 상품 이미지 주소
     */
    private String productImage;

    /***
     * 상품 평점
     */
    private double rating;

    /***
     * 상품 배송소요일
     */
    private int requiredDay;

    /***
     * 상품 판매상태
     * 0: 판매 준비중 / 1: 판매 중 / 2: 판매 종료
     */
    private int status;

    /***
     * 상품 기본키
     */
    @Transient
    private int reviewCount; // 상품 평점 수

}
