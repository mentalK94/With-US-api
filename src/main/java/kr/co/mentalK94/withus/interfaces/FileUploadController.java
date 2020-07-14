package kr.co.mentalK94.withus.interfaces;

import kr.co.mentalK94.withus.applications.ProductImageService;
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
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileUploadController {

    private static final String SAVE_PRODUCT_PATH = "D:/hansol/With-us/with-us-api/With-US-api/build/resources/main/static/img/";
    private static final String SAVE_PRODUCT_INFO_PATH = "D:/hansol/With-us/with-us-api/With-US-api/build/resources/main/static/productInfoImages/";

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    @PostMapping("/upload/{id}")
    public String upload(@PathVariable("id") Long id,
                         @RequestParam("productImage")MultipartFile file
                         ) throws Exception {

        productService.imageUpdate(SAVE_PRODUCT_PATH, file, id);

        return "file has uploaded successfully";
    }

    @PostMapping("/upload/productImages/{id}")
    public String uploadImageFiles(@PathVariable("id") Long id,
                         @RequestParam("productImage1")MultipartFile file1,
                         @RequestParam("productImage2")MultipartFile file2,
                         @RequestParam("productImage3")MultipartFile file3,
                         @RequestParam("productImage4")MultipartFile file4
    ) throws Exception {

        List<MultipartFile> imageFiles = new ArrayList<>();

        imageFiles.add(file1);
        imageFiles.add(file2);
        imageFiles.add(file3);
        imageFiles.add(file4);

        productImageService.imagesUpdate(SAVE_PRODUCT_INFO_PATH, imageFiles, id);

        return "file has uploaded successfully";
    }
}
