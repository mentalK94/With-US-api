package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {

    List<Product> selectProductList() throws Exception;

    Product selectProduct() throws Exception;

    void insertProduct(Product product);
}
