package kr.co.mentalK94.withus.interfaces;

import lombok.Data;

@Data
public class LoginRequestDTO { // 이 DTO의 역할이 무엇인지 정확히 알기

    private String email;

    private String password;

    private String name;

    private Long userId;
}
