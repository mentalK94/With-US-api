package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Customer;

public interface CustomerService {

    /***
     * 고객 추가
     */
    void createUser(Customer customer) throws Exception;

    /***
     * 고객 인증
     */
    Customer authenticate(String email, String password);

    /***
     * 고객 포인트 사용
     */
    void updateUsedPoint(Long customerId, int usedPoint);

    /***
     * 고객 조회
     */
    Customer findCustomerById(Long customerId);

    /***
     * 고객 장바구니 변경
     */
    void updateCartMyCustomer(Customer customer);

    /***
     * 고객 권한 변경
     */
    void updateAuth(Long userId, int auth, String initKey);

    /***
     * 이메일을 이용한 고객 조회
     */
    Customer findCustomerByEmail(String email);
}
