package kr.co.mentalK94.withus.interfaces;

import kr.co.mentalK94.withus.applications.ProductService;
import kr.co.mentalK94.withus.domains.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> list() throws Exception {
        return productService.getProductList();
    }

    @GetMapping("/products/{id}")
    public Product detail(@PathVariable("id") Long id) throws Exception {
        return productService.getProduct(id);
    }

    @PostMapping("/products")
    public ResponseEntity<?> create(@RequestBody Product resource) throws URISyntaxException {

        Product product = Product.builder().name(resource.getName())
                                           .category(resource.getCategory())
                                            .price(resource.getPrice())
                                            .manufacturer(resource.getManufacturer())
                                            .stock(resource.getStock())
                                            .description(resource.getDescription())
                                            .build();

        productService.addProduct(product);

        URI location = new URI("/products/" + product.getId());
        return ResponseEntity.created(location).body("product insert success");
    }

    @PatchMapping("/products/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody Product resource) {

        Product product = Product.builder().name(resource.getName())
                .category(resource.getCategory())
                .price(resource.getPrice())
                .manufacturer(resource.getManufacturer())
                .stock(resource.getStock())
                .description(resource.getDescription())
                .build();
        productService.updateProduct(product, id);
        return "product update success";
    }
}
