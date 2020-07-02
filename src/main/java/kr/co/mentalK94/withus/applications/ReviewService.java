package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Product;
import kr.co.mentalK94.withus.domains.Review;
import kr.co.mentalK94.withus.mappers.ProductMapper;
import kr.co.mentalK94.withus.mappers.ReviewMapper;
import kr.co.mentalK94.withus.utils.RatingCalculationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    ProductMapper productMapper;

    Logger logger = LoggerFactory.getLogger(ReviewService.class);

    public void addReview(Review review) throws Exception {

        // 상품 평점 갱신(항목 : 리뷰 수, 평점 계산)
        Product product = productMapper.selectProduct(review.getProductId());

        double updatedRating = RatingCalculationUtil.calculate(product.getReviewCount(), product.getRating(), review.getRating());

        productMapper.updateProductReview(product.getReviewCount()+1, updatedRating, product.getId());

        // 리뷰 추가
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
