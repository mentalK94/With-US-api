package kr.co.mentalK94.withus.daoInterfaces;

import kr.co.mentalK94.withus.domains.Product;

import java.util.List;

public interface ProductDAO {

    public void insertProduct(Product product); // Product INSERT

    public Product selectProduct(Long id); // Product Detail

    public List<Product> selectProductList(); // Product List
}
