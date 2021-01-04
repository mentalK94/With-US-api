package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.User;
import kr.co.mentalK94.withus.mappers.UserMapper;
import kr.co.mentalK94.withus.utils.MailUtil;
import kr.co.mentalK94.withus.utils.TempKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailService mailService;

    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    UserService(PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public void addUser(User user) throws UnsupportedEncodingException, MessagingException {

        // 패스워드 해싱
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        // autoKey생성
        String authKey = new TempKeyUtil().getKey(50, false);

        user.setAuthKey(authKey);
        userMapper.insertUser(user);

        mailService.sendAuthMail(user, authKey);
    }


    // 인증 로직
    public User authenticate(String email, String password) {
        User user = userMapper.selectByUserEmail(email);

        if(user == null) { // 해당 이메일이 존재하지 않는 경우
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

    public User getUserById(Long userId) {
        return userMapper.selectByUserId(userId);
    }

    public void updateCartMyUser(User user) {
        userMapper.updateCartByUserId(user);
    }

    public void updateAuth(Long userId, int auth, String initKey) {
        userMapper.updateAuth(userId, auth, initKey);
    }

    // email을 통한 User검색
    public User getUserByEmail(String email) {
        return userMapper.selectByUserEmail(email);
    }
}
