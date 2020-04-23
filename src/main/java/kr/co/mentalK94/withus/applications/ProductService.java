package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Product;
import kr.co.mentalK94.withus.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<Product> getProductList() throws Exception {
        return productMapper.selectProductList();
    }

    public Product getProduct(Long id) throws Exception {
        return productMapper.selectProduct(id);
    }

    public void addProduct(Product product) {
        productMapper.insertProduct(product);
    }
}
