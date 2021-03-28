package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Customer;
import kr.co.mentalK94.withus.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Transactional
    @Async
    public void sendAuthMail(Customer customer, String authKey) throws MessagingException, UnsupportedEncodingException {
        // mail 작성 관련
        MailUtil sendMail = new MailUtil(mailSender);

        sendMail.setSubject("[With Us 위드어스] 회원가입 이메일 인증");
        sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                .append("<a href='http://localhost:9306/registerConfirm?uid=")
                .append(customer.getId())
                .append("&email=")
                .append(customer.getEmail())
                .append("&authKey=")
                .append(authKey)
                .append("' target='_blank'>이메일 인증 확인</a>")
                .toString());
        sendMail.setFrom("doingnow94@gmail.com ", "위드어스");
        sendMail.setTo(customer.getEmail());
        sendMail.send();
    }
}
