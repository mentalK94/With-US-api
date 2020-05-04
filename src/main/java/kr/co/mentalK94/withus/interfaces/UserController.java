package kr.co.mentalK94.withus.interfaces;

import kr.co.mentalK94.withus.applications.UserService;
import kr.co.mentalK94.withus.domains.User;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {

        User user = User.builder()
                        .email(resource.getEmail())
                        .password(resource.getPassword())
                        .name(resource.getName())
                        .phone(resource.getPhone())
                        .address(resource.getAddress())
                        .build();

        userService.addUser(user);

        String url = "users/" + user.getId();
        return ResponseEntity.created(new URI(url)).body("user create successfully");
    }
}
