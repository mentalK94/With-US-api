package kr.co.mentalK94.withus.repository;

import kr.co.mentalK94.withus.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /***
     * 아이디를 이용한 고객 조회
     */
    Optional<Customer> findById(Long id);

    /***
     * 이메일을 이용한 고객 조회
     */
    Optional<Customer> findByEmail(String email);

    /***
     * 고객 포인트 조회
     */
    int findPointById(Long id);

    /***
     * 고객 포인트 업데이트
     */
    @Query(value = "UPDATE customer SET point =:point WHERE id = :customerId", nativeQuery = true)
    void updatePointById(@Param("customerId") Long customerId, @Param("point") int point);

    /***
     * 고객 유저상태 업데이트
     */
    @Query(value = "UPDATE customer SET auth =:auth, authKey = :initKey WHERE id = :customerId", nativeQuery = true)
    void updateAuth(@Param("customerId") Long customerId, @Param("auth") int auth, @Param("initKey") String initKey);
}
