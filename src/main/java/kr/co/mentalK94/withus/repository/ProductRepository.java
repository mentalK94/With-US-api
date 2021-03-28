package kr.co.mentalK94.withus.repository;

import kr.co.mentalK94.withus.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /***
     * 아이디를 이용한 상품 조회
     */
    Optional<Product> findById(Long id);

    /***
     * 아이디를 이용한 상품 조회
     */
    List<Product> findProductTodayList();

    /***
     * 이미지 업데이트
     */
    @Query(value = "UPDATE product SET product_image = :productImage WHERE id =: id", nativeQuery = true)
    void imageUpdate(@Param("id") Long id, @Param("productImage") String productImage);
}
