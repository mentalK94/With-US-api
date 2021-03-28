package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CategoryMapper {

    void insertCategory(Category category);

    Category selectCategoryById(Long id);
}
