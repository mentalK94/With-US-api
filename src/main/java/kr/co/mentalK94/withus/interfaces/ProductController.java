package kr.co.mentalK94.withus.interfaces;

import ch.qos.logback.core.CoreConstants;
import kr.co.mentalK94.withus.applications.ProductService;
import kr.co.mentalK94.withus.domains.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> create(@RequestBody Product resource, HttpServletRequest request) throws URISyntaxException {

        Product product = Product.builder().name(resource.getName())
                                           .category(resource.getCategory())
                                            .price(resource.getPrice())
                                            .manufacturer(resource.getManufacturer())
                                            .stock(resource.getStock())
                                            .description(resource.getDescription())
                                            .build();

        MultipartFile productImage = product.getProductImage();
        String rootDir = request.getSession().getServletContext().getRealPath("D:\\hansol\\With-us\\");
        Path savePath = Paths.get(rootDir+"With-US-Web\\public\\img\\uploadImages\\"+productImage.getOriginalFilename());

        if(!productImage.isEmpty()) {
            System.out.println("============= file start ===============");
            System.out.println("name : " + productImage.getName());
            System.out.println("filename : " + productImage.getOriginalFilename());
            System.out.println("size: " + productImage.getSize());
            System.out.println("savePath : " + savePath);
            System.out.println("============= file end =================");
        }

        if(productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(savePath.toString()));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }

        product.setImageFileName(productImage.getOriginalFilename());

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

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        int deleteResult = productService.deleteProduct(id);

        if(deleteResult == 0) { // 삭제가 되지 않은 경우
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
