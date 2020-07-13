package kr.co.mentalK94.withus.domains;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImages {

    private Long id;

    private Long productId;

    private String productImage1;

    private String productImage2;

    private String productImage3;

    private String productImage4;
}