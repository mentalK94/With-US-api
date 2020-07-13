package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Product;
import kr.co.mentalK94.withus.mappers.ProductMapper;
import kr.co.mentalK94.withus.utils.FilenameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public void imageUpdate(String savePath, MultipartFile file, Long id) throws IOException {

        // 파일명 생성 메서드 호출
        String filename = FilenameUtil.make(file.getOriginalFilename());

        File convertFile = new File(savePath +  filename);
        convertFile.createNewFile();

        try(FileOutputStream fout = new FileOutputStream(convertFile)) {
            fout.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        productMapper.imageUpdate(filename, id);
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