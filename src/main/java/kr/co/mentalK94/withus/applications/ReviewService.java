package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.Review;
import kr.co.mentalK94.withus.mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    public void addReview(Review review) {
        reviewMapper.insertReview(review);
    }
}
