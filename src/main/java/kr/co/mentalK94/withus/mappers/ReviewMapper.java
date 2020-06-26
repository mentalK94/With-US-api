package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewMapper {

    void insertReview(Review review);

    List<Review> selectReviewByProductId(Long productId);

    int deleteReview(@Param("userId")Long userId, @Param("reviewId")Long reviewId);
}
