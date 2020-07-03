package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.User;
import kr.co.mentalK94.withus.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void addUser(User user) {

        // 패스워드 해싱
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        // logger.info(encodedPassword);
        user.setPassword(encodedPassword);

        userMapper.insertUser(user);

        // autoKey생성
        String authKey = new TempKey().getKey(50, false);

        // mail 작성 관련
        MailUtils sendMail = new MailUtils(mailSender);

        sendMail.setSubject("[With Us 위드어스] 회원가입 이메일 인증");
        sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                .append("<a href='http://localhost:9002/users/joinConfirm?uid=")
                .append(user.getUid())
                .append("&email=")
                .append(user.getEmail())
                .append("&authkey=")
                .append(authKey)
                .append("' target='_blenk'>이메일 인증 확인</a>")
                .toString());
        sendMail.setFrom("위드어스 ", "김한솔");
        sendMail.setTo(user.getEmail());
        sendMail.send();

    }


    // 로그로직
    public User authenticate(String email, String password) {
        User user = userMapper.selectByUserEmail(email);

        if(user == null) { // email이 존재 하는 경우
            throw new AuthenticationWrongException();
        }

        if(!passwordEncoder.matches(password, user.getPassword())) { // �스�드가 �치�� �는 경우
            throw new AuthenticationWrongException();
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
}
