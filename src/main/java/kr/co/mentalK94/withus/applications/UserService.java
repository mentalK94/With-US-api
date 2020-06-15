package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.User;
import kr.co.mentalK94.withus.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    UserService(PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public void addUser(User user) {

        // logger.info(user.getPassword());

        // 패스워드 해싱
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        // logger.info(encodedPassword);
        user.setPassword(encodedPassword);

        userMapper.insertUser(user);
    }


    // 로그로직
    public User authenticate(String email, String password) {
        User user = userMapper.selectByUserEmail(email);

        if(user == null) { // email존재�� �는 경우
            throw new AuthenticationWrongException();
        }

        if(!passwordEncoder.matches(password, user.getPassword())) { // �스�드가 �치�� �는 경우
            throw new AuthenticationWrongException();
        }

        return user;
    }

    public User getMyUser(Long userId) {
        return userMapper.selectByUserId(userId);
    }

    public void updateCartMyUser(User user) {
        userMapper.updateCartByUserId(user);
    }
}
