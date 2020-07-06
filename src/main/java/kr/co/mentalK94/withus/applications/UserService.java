package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.User;
import kr.co.mentalK94.withus.mappers.UserMapper;
import kr.co.mentalK94.withus.utils.MailUtil;
import kr.co.mentalK94.withus.utils.TempKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JavaMailSender mailSender;

    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    UserService(PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public void addUser(User user) throws MessagingException, UnsupportedEncodingException {

        // 패스워드 해싱
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        // logger.info(encodedPassword);
        user.setPassword(encodedPassword);

        // autoKey생성
        String authKey = new TempKeyUtil().getKey(50, false);

        user.setAuthKey(authKey);
        userMapper.insertUser(user);

        // mail 작성 관련
        MailUtil sendMail = new MailUtil(mailSender);

        sendMail.setSubject("[With Us 위드어스] 회원가입 이메일 인증");
        sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                .append("<a href='http://localhost:9306/registerConfirm?uid=")
                .append(user.getId())
                .append("&email=")
                .append(user.getEmail())
                .append("&authKey=")
                .append(authKey)
                .append("' target='_blank'>이메일 인증 확인</a>")
                .toString());
        sendMail.setFrom("doingnow94@gmail.com ", "위드어스");
        sendMail.setTo(user.getEmail());
        sendMail.send();

    }


    // 로그로직
    public User authenticate(String email, String password) {
        User user = userMapper.selectByUserEmail(email);

        if(user == null) { // email이 존재 하는 경우
            //throw new AuthenticationWrongException();
            return null;
        }

        if(!passwordEncoder.matches(password, user.getPassword())) { // 패스워드가 일치하지 않는 경우
            // throw new AuthenticationWrongException();
            return null;
        }

        return user;
    }

    public void updateUsingPoint(Long userId, int usingPoint) {

        // logger.info("selectPointByUserId(userId) : " + userMapper.selectPointByUserId(userId));

        // 유저 포인트 조회
        int point = userMapper.selectPointByUserId(userId);

        // point 갱신
        point -= usingPoint;

        // 갱신된 포인트 저장
        userMapper.updatePointByUserId(userId, point);
    }

    public User getMyUser(Long userId) {
        return userMapper.selectByUserId(userId);
    }

    public void updateCartMyUser(User user) {
        userMapper.updateCartByUserId(user);
    }

    public void updateAuth(Long userId, int auth, String initKey) {
        userMapper.updateAuth(userId, auth, initKey);
    }

    public User getMyUserByEmail(String email) {
        return userMapper.selectByUserEmail(email);
    }
}
