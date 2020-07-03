package kr.co.mentalK94.withus.domains;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id; // user id

    private String email; // user email

    private String password; // user password

    private String name; // user name

    private String phone; // user phone

    private String address; // user 주소

    private String zonecode; // user 우편번호

    private String detailAddress; // user 상세주소

    private int point; // user point

    private int auth; // user auth (비활성화 : 0 / common user : 1 / admin : 2)

    private String authKey; // 인증 키
}
