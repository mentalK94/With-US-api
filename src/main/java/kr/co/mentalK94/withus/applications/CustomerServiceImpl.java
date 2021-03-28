package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Customer;
import kr.co.mentalK94.withus.repository.CustomerRepository;
import kr.co.mentalK94.withus.utils.TempKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MailService mailService;

    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public void createUser(Customer customer) throws Exception {

        // 패스워드 해싱
        String encodedPassword = new BCryptPasswordEncoder().encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        // autoKey생성
        String verificationKey = new TempKeyUtil().getKey(50, false);

        customer.setVerificationKey(verificationKey);
        customerRepository.save(customer);

        mailService.sendAuthMail(customer, verificationKey);
    }

    @Override
    public Customer authenticate(String email, String password) {

        Customer customer = customerRepository.findByEmail(email).orElseThrow(AuthenticationWrongException::new);

        if(!passwordEncoder.matches(password, customer.getPassword())) {
            throw new AuthenticationWrongException();
        }

        return customer;
    }

    @Override
    public void updateUsedPoint(Long customerId, int usingPoint) {

        // logger.info("selectPointByUserId(userId) : " + userMapper.selectPointByUserId(userId));

        // 고객 포인트 조회
        int point = customerRepository.findPointById(customerId);

        // point 갱신
        point -= usingPoint;

        // 갱신된 포인트 저장
        customerRepository.updatePointById(customerId, point);
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public void updateCartMyCustomer(Customer customer) {
//        customerRepository.updateCartByUserId(customer);
    }

    @Override
    public void updateAuth(Long customerId, int auth, String initKey) {
        customerRepository.updateAuth(customerId, auth, initKey);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }
}
