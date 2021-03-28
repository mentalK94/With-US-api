package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Product;
import kr.co.mentalK94.withus.repository.ProductRepository;
import kr.co.mentalK94.withus.utils.FilenameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final static int PAGE_SIZE = 20;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findProductList(int page) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page, PAGE_SIZE, Sort.by("id").descending()));
        return products.getContent();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void imageUpdate(String savePath, MultipartFile file, Long id) throws IOException {

        // 파일명 생성 메서드 호출
        String productImage = FilenameUtil.make(file.getOriginalFilename());

        File convertFile = new File(savePath +  productImage);
        convertFile.createNewFile();

        try(FileOutputStream fout = new FileOutputStream(convertFile)) {
            fout.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        productRepository.imageUpdate(id, productImage);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductTodayList() {
        return productRepository.findProductTodayList();
    }
}