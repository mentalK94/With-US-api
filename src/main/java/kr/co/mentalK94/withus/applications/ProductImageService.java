package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.ProductImage;
import kr.co.mentalK94.withus.mappers.ProductImageMapper;
import kr.co.mentalK94.withus.utils.FilenameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageService {

    @Autowired
    ProductImageMapper productImageMapper;


    public void imagesUpdate(String savePath, List<MultipartFile> files, Long id) throws IOException {

        List<String> filenames = new ArrayList<>();

        for(MultipartFile file : files) {

            if(file != null) {
                String filename = FilenameUtil.make(file.getOriginalFilename());

                filenames.add(filename);

                File convertFile = new File(savePath +  filename);
                convertFile.createNewFile();

                try(FileOutputStream fout = new FileOutputStream(convertFile)) {
                    fout.write(file.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        ProductImage productImage = ProductImage.builder()
                .productId(id)
                .productImage1(filenames.get(0))
                .productImage2(filenames.get(1))
                .productImage3(filenames.get(2))
                .productImage4(filenames.get(3))
                .build();

        productImageMapper.insertImages(productImage);
    }
}
