package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Review;
import kr.co.mentalK94.withus.mappers.ReviewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    Logger logger = LoggerFactory.getLogger(ReviewService.class);

    public void addReview(Review review) {
        reviewMapper.insertReview(review);
    }

    public List<Review> getReviewListByProductId(Long productId) {
        return reviewMapper.selectReviewByProductId(productId);
    }

    public boolean deleteReview(Long userId, Long reviewId) {

        // 삭제할 리뷰가 있는지 여부 확인
        int cnt = reviewMapper.deleteReview(userId, reviewId);

        logger.info("cnt : " + cnt);

        if(cnt <= 0) {
            return false;
        } else {
            return true;
        }

    }
}
