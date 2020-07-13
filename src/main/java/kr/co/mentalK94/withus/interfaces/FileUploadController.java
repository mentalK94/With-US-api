package kr.co.mentalK94.withus.interfaces;

import kr.co.mentalK94.withus.applications.ProductService;
import kr.co.mentalK94.withus.domains.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalTime;

@RestController
public class FileUploadController {

    private static final String SAVE_PRODUCT_PATH = "D:/hansol/With-us/with-us-api/With-US-api/build/resources/main/static/img/";
    private static final String SAVE_PRODUCT_INFO_PATH = "D:/hansol/With-us/with-us-api/With-US-api/build/resources/main/static/productInfoImages/";

    @Autowired
    private ProductService productService;

    @PostMapping("/upload/{id}")
    public String upload(@PathVariable("id") Long id,
                         @RequestParam("productImage")MultipartFile file
                         ) throws Exception {

        productService.imageUpdate(SAVE_PRODUCT_PATH, file, id);

        return "file has uploaded successfully";
    }
}
