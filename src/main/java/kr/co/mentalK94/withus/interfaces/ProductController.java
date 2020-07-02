package kr.co.mentalK94.withus.interfaces;

import kr.co.mentalK94.withus.applications.ProductService;
import kr.co.mentalK94.withus.domains.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ResponseEntity<?> create(@RequestBody Product resource,
                                    BindingResult result,
                                    HttpServletRequest request)
            throws URISyntaxException {

        if(result.hasErrors()) {
            System.out.println("form data has some errors");
            List<ObjectError> errors = result.getAllErrors();

            for(ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
            }
        }

        Product product = Product.builder().name(resource.getName())
                                           .category(resource.getCategory())
                                            .price(resource.getPrice())
                                            .manufacturer(resource.getManufacturer())
                                            .stock(resource.getStock())
                                            .description(resource.getDescription())
                                            .limitedQuantity(resource.getLimitedQuantity())
                                            .build();

        // product.setImageFileName(productImage.getOriginalFilename());

        productService.addProduct(product);

        URI location = new URI("/products/" + product.getId());
        return ResponseEntity.created(location).body(product.getId());
    }

    @PatchMapping("/products/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody Product resource) {

        Product product = Product.builder().name(resource.getName())
                .category(resource.getCategory())
                .price(resource.getPrice())
                .manufacturer(resource.getManufacturer())
                .stock(resource.getStock())
                .description(resource.getDescription())
                .rating(resource.getRating())
                .limitedQuantity(resource.getLimitedQuantity())
                .build();

        productService.updateProduct(product, id);
        return "product update success";
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        int deleteResult = productService.deleteProduct(id);

        if(deleteResult == 0) { // 삭제가 되지 않은 경우
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
