package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.ProductImage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductImageMapper {

    void insertImages(ProductImage productImages);
}
