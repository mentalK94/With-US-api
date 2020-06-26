package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewMapper {

    void insertReview(Review review);
}
