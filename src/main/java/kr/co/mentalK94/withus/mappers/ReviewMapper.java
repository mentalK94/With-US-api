package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReviewMapper {

    void insertReview(Review review);
}
