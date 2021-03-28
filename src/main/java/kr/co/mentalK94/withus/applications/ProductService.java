package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    /***
     * Page별로 상품조회
     * 1page size: 20
     */
    List<Product> findProductList(int page);

    /***
     * id를 이용한 상품조회
     */
    Product findProductById(Long id);

    /***
     * 상품 추가
     */
    void createProduct(Product product);

    /***
     * 상품 정보 수정
     */
    void updateProduct(Product product);

    /***
     * 상품 이미지 추가
     */
    void imageUpdate(String savePath, MultipartFile file, Long id) throws IOException;

    /***
     * 상품 삭제
     */
    void deleteProduct(Long id);

    /***
     * 오늘의 상품 추천
     */
    List<Product> findProductTodayList();
}
