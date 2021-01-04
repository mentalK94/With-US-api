package kr.co.mentalK94.withus.interfaces;

import kr.co.mentalK94.withus.applications.UserService;
import kr.co.mentalK94.withus.domains.User;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static String INIT_KEY = "DSFsfNW1f5615ds1fq623dfkj48wqKDDF55IJ";

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException, UnsupportedEncodingException, MessagingException {

        // 해당 이메일을 사용하고 있는 User가 존재하는지 확인
        if(userService.getUserByEmail(resource.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 응답
        }

        User user = User.builder()
                        .email(resource.getEmail())
                        .password(resource.getPassword())
                        .name(resource.getName())
                        .phone(resource.getPhone())
                        .address(resource.getAddress())
                        .zonecode(resource.getZonecode())
                        .detailAddress(resource.getDetailAddress())
                        .build();

        userService.addUser(user);

        String url = "users/" + user.getId();
        return ResponseEntity.created(new URI(url)).body("user create successfully");
    }

    @GetMapping("/users/registerConfirm")
    public ResponseEntity<?> confirm(@RequestParam("uid")Long userId, @RequestParam("email")String email,
                          @RequestParam("authKey")String authKey) {
        User user = userService.getMyUser(userId);

        if(authKey.equals(user.getAuthKey())) { // authKey가 같은 경우 -> 응답코드 201
            logger.info(user.getEmail() + ": auth confirmed");
            userService.updateAuth(userId, 1, INIT_KEY);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else if(INIT_KEY.equals(user.getAuthKey())) { // 이미 인증을 한 경우 -> 응답코드 208
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } else { // 인증코드가 다른 경우 -> 응답코드 400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/users/emailCheck")
//    public ResponseEntity<?> emailConfirm(@RequestParam("email")String email) {
//        User user = userService.getMyUserByEmail(email);
//
//        if(user != null) { // 이메일이 이미 존재하는 경우
//            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 응답
//        }
//
//        return new ResponseEntity<>(HttpStatus.OK); // 200 응답
//    }
}
