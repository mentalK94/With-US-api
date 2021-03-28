package kr.co.mentalK94.withus.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.withus.applications.CustomerServiceImpl;
import kr.co.mentalK94.withus.domains.Customer;
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
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private JWTUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO resource
    ) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();

        Customer customerAuthCheck = customerServiceImpl.findCustomerByEmail(email);

        if(customerAuthCheck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 해당 계정이 존재하지 않는 경우 -> response 404
        }

        if(customerAuthCheck.getAuth() == 0) { // 인증되지 않은 계정인 경우 -> response 401
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Customer customer = customerServiceImpl.authenticate(email, password);

        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 비밀번호가 일치하지 않는 경우 -> response 404
        }

        String accessToken = jwtUtil.crateToken(customer.getId(), customer.getName());

        logger.info(accessToken);
        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder().accessToken(accessToken).build();

        String url = "/login";

        return ResponseEntity.created(new URI(url)).body(loginResponseDTO);
    }

    @PostMapping("/loginUser")
    public Customer loginUser(Authentication authentication) {
        
        Claims claims = (Claims) authentication.getPrincipal();

        Long customerId = claims.get("customerId", Long.class);

        Customer customer = customerServiceImpl.findCustomerById(customerId);
        Customer loginCustomer = Customer.builder().id(customer.getId()).address(customer.getAddress())
                .email(customer.getEmail()).name(customer.getName())
                .phoneNumber(customer.getPhoneNumber()).point(customer.getPoint())
                .zoneCode(customer.getZoneCode()).detailAddress(customer.getDetailAddress())
                .build();

//        logger.info("loginUser : " + loginUser.getName());
        return loginCustomer;
    }
}
