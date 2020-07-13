package kr.co.mentalK94.withus.domains;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {

    private Long id;

    private Long productId;

    private List<String> productImages;
}
