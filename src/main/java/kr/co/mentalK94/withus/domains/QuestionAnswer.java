package kr.co.mentalK94.withus.domains;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionAnswer {

    private Long id;

    private Long productId;

    private String question;

    private String questionWriter;

    private String answer;

    private String answerWriter;

    private String questionRegisterDate;

    private String answerRegisterDate;
}
