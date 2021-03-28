package kr.co.mentalK94.withus.interfaces;

import kr.co.mentalK94.withus.applications.ProductService;
import kr.co.mentalK94.withus.domains.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api}/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/page/{page}")
    public List<Product> list(@PathVariable("page") int page) throws Exception {
        return productService.findProductList(page);
    }

    @GetMapping("/recommend")
    public List<Product> todayList() throws Exception {
        return productService.findProductTodayList();
    }

//    @GetMapping("/categories/{categoryId}")
//    public List<Product> listByCategory(@PathVariable("categoryId") Long categoryId) throws Exception {
//        return productServiceImpl.getProductListByCategoryId(categoryId);
//    }

    @GetMapping("/{id}")
    public Product detail(@PathVariable("id") Long id) {
        return productService.findProductById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Product resource,
                                    BindingResult result) {

        if(result.hasErrors()) {
            System.out.println("form data has some errors");
            List<ObjectError> errors = result.getAllErrors();

            for(ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
            }
        }

        Product product = Product.builder().name(resource.getName())
                                            .price(resource.getPrice())
                                            .description(resource.getDescription())
                                            .limitedQuantity(resource.getLimitedQuantity())
                                            .build();

        productService.createProduct(product);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public String update(@RequestBody Product resource) {

        Product product = Product.builder()
                .id(resource.getId())
                .name(resource.getName())
                .price(resource.getPrice())
                .description(resource.getDescription())
                .limitedQuantity(resource.getLimitedQuantity())
                .build();

        productService.updateProduct(product);
        return "product update success";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
