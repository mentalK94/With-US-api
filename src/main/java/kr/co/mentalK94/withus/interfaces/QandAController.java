package kr.co.mentalK94.withus.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.withus.applications.QAndAService;
import kr.co.mentalK94.withus.domains.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class QandAController {

    @Autowired
    private QAndAService qandAService;

    @PostMapping("/write/question")
    public ResponseEntity<?> writeQuestion(Authentication authentication,
                                           @RequestBody QuestionAnswer resource) throws URISyntaxException {

        // Customer -> Question 작성
        String username = getUserName(authentication);

        QuestionAnswer questionAnswer = QuestionAnswer.builder().productId(resource.getProductId())
                                                        .question(resource.getQuestion())
                                                        .questionWriter(username)
                                                        .build();

        qandAService.writeQuestion(questionAnswer);

        URI location = new URI("/write/question/"+questionAnswer.getId());
        return ResponseEntity.created(location).body(questionAnswer.getId());

    }

    @PostMapping("/write/answer")
    public ResponseEntity<?> writeAnswer(Authentication authentication,
                                           @RequestBody QuestionAnswer resource) throws URISyntaxException {

        // admin -> answer 작성
        String admin = getUserName(authentication);

        qandAService.writeAnswer(resource.getId(), resource.getAnswer(), admin);

        return ResponseEntity.ok("answer success");

    }

    private String getUserName(Authentication authentication) {
        Claims claims = (Claims) authentication.getPrincipal();
        return claims.get("name", String.class);
    }
}
