package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.User;
import kr.co.mentalK94.withus.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMaapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMaapper, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userMaapper = userMaapper;
    }

    public void addUser(User user) {
        userMaapper.insertUser(user);
    }


    // 로그인 로직
    public User authenticate(String email, String password) {
        User user = userMaapper.selectByUserEmail(email);

        if(user == null) { // email이 존재하지 않는 경우
            throw new AuthenticationWrongException();
        }

        if(!passwordEncoder.matches(password, user.getPassword())) { // 패스워드가 일치하지 않는 경우
            throw new AuthenticationWrongException();
        }

        return user;
    }
}
