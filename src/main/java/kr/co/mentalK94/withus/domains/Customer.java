package kr.co.mentalK94.withus.domains;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    /**
     * 고객 entity의 기본키
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 고객 이메일
     * */
    private String email;

    /**
     * 고객 비밀번호
     * */
    private String password;

    /**
     * 고객 이름
     * */
    private String name;

    /**
     * 고객 연락처
     * */
    private String phoneNumber;

    /**
     * 고객 거주지 주소
     * */
    private String address;

    /**
     * 고객 거주지 우편번호
     * */
    private String zoneCode;

    /**
     * 고객 거주지 상세주소
     * */
    private String detailAddress;

    /**
     * 고객 포인트
     * */
    private int point;

    /**
     * 고객 상태
     * 비활성화 : 0 / 인증되지 않은 고객 : 1 / common user : 2 / admin : 3
     * */
    private int auth;

    /**
     * 고객 인증키
     * */
    private String verificationKey;

    /**
     * 고객 생성일자
     * */
    private LocalDateTime createdAt;

    /**
     * 고객 생성자
     * */
    private String createdBy;

    /**
     * 고객 수정일자
     * */
    private LocalDateTime updatedAt;

    /**
     * 고객 수정일자
     * */
    private String updatedBy;
}
