package kr.co.mentalK94.withus.domains;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Long id;

    private String categoryName;
}
