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

    private int auth; // user auth (admin : 0 / common user : 1)
}
