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

    private String address; // user address

    private int point; // user point

    private int auth; // user auth (admin : 0 / common user : 1)
}
