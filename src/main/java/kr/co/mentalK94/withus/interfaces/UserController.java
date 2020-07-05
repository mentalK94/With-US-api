package kr.co.mentalK94.withus.interfaces;

import kr.co.mentalK94.withus.applications.UserService;
import kr.co.mentalK94.withus.domains.User;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException, UnsupportedEncodingException, MessagingException {

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
    public String confirm(@RequestParam("uid")Long userId, @RequestParam("email")String email,
                          @RequestParam("authKey")String authKey) {
        User user = userService.getMyUser(userId);

        // authKey가 같은 경우
        if(authKey.equals(user.getAuthKey())) {
            logger.info(user.getEmail() + ": auth confirmed");
            userService.updateAuth(userId, 1);
            return "auth confirmed";
        } else { // 이미 인증 했거나 authkey가 다른 경우
            return "";
        }
    }
}
