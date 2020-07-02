package kr.co.mentalK94.withus.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.withus.applications.ReviewService;
import kr.co.mentalK94.withus.applications.UserService;
import kr.co.mentalK94.withus.domains.Review;
import kr.co.mentalK94.withus.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/products/{productId}/reviews")
    public List<Review> list(@PathVariable("productId") Long productId) { // 상품에 맞는 리뷰 리스트 가져오기
        return reviewService.getReviewListByProductId(productId);
    }

    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<?> create(Authentication authentication,
                                    @PathVariable("productId") Long productId,
                                    @RequestBody Review resource)
            throws Exception {

        Claims claims = (Claims) authentication.getPrincipal();

        String name = claims.get("name", String.class);

        Long userId = claims.get("userId", Long.class);

        Review review = Review.builder().productId(productId)
                                        .userId(userId)
                                        .rating(resource.getRating())
                                        .description(resource.getDescription())
                                        .writer(name)
                                        .build();

        reviewService.addReview(review);

        URI location = new URI("/products/" + productId + "/reviews/" + review.getId());
        return ResponseEntity.created(location).body(review.getId());
    }

    @DeleteMapping("/products/reviews/{id}")
    public ResponseEntity<?> remove(Authentication authentication,
                                    @PathVariable("id") Long reviewId) {

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);

        boolean isRemove = reviewService.deleteReview(userId, reviewId);

        if(!isRemove) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reviewId, HttpStatus.OK);
    }
}
