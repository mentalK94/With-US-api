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

    public void updateProduct(Product product, Long id) {
        productMapper.updateProduct(product, id);
    }

    public void imageUpdate(String imageFileName, Long id) {
        productMapper.imageUpdate(imageFileName, id);
    }

    public int deleteProduct(Long id) {
        return productMapper.deleteProduct(id);
    }

    public List<Product> getProductListByCategoryId(Long categoryId) throws Exception {
        return productMapper.selectProductByCategoryId(categoryId);
    }

    public List<Product> getProductTodayList() {
        return productMapper.selectProductTodayList();
    }
}