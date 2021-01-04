package kr.co.mentalK94.withus.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.withus.applications.UserService;
import kr.co.mentalK94.withus.domains.User;
import kr.co.mentalK94.withus.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin // cors 허용
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO resource
    ) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();

        User userAuthCheck = userService.getUserByEmail(email);

        if(userAuthCheck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 해당 계정이 존재하지 않는 경우 -> response 404
        }

        if(userAuthCheck.getAuth() == 0) { // 인증되지 않은 계정인 경우 -> response 401
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = userService.authenticate(email, password);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 비밀번호가 일치하지 않는 경우 -> response 404
        }

        String accessToken = jwtUtil.crateToken(user.getId(), user.getName());

        logger.info(accessToken);
        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder().accessToken(accessToken).build();

        String url = "/login";

        return ResponseEntity.created(new URI(url)).body(loginResponseDTO);
    }

    @PostMapping("/loginUser")
    public User loginUser(Authentication authentication) {
        
        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("userId", Long.class);

        User user = userService.getMyUser(userId);
        User loginUser = User.builder().id(user.getId()).address(user.getAddress())
                .email(user.getEmail()).name(user.getName())
                .phone(user.getPhone()).point(user.getPoint())
                .zonecode(user.getZonecode()).detailAddress(user.getDetailAddress())
                .build();

//        logger.info("loginUser : " + loginUser.getName());
        return loginUser;
    }
}
