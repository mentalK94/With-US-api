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
    private UserMapper userMaapper;

    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public void addUser(User user) {

        logger.info(user.getPassword());

        // 패스워드 암호화(해싱)
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        logger.info(encodedPassword);
        user.setPassword(encodedPassword);

        userMaapper.insertUser(user);
    }
}
