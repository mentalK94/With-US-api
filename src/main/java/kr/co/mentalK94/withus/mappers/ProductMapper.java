package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {

    List<Product> selectProductList() throws Exception;

    Product selectProduct(Long id) throws Exception;

    int selectProductStock(Long id);

    void insertProduct(Product product);

    void updateProduct(@Param("product") Product product, @Param("id")Long id);

    void updateProductStock(@Param("productId")Long id, @Param("stock")int stock);

    void imageUpdate(String imageFileName, @Param("id")Long id);

    int deleteProduct(Long id);
}