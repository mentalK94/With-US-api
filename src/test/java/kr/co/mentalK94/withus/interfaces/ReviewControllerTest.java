package kr.co.mentalK94.withus.interfaces;

import kr.co.mentalK94.withus.applications.PurchaseService;
import kr.co.mentalK94.withus.applications.ReviewService;
import kr.co.mentalK94.withus.domains.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void create() throws Exception {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEzLCJuYW1lIjoi6rmA7ZWc7IaUIn0.w7NBLohkGUErcavoLkSLzFdk660PXaGCPw6Jvk6sk0M";

        Review mockReview = Review.builder().productId(3L).rating(3.0).writer("hansol").description("품질이 뛰어나요").build();


//        given(reviewService.addReview(3L, "hansol", 3.0, "품질이 뛰어나요")).willReturn(
//
//        );

        mvc.perform(post("/products/3/reviews")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"productId\": 3L, \"rating\": 3.0, \"description\": \"품질이 뛰어나요\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/products/3/reviews/1"));

        verify(reviewService).addReview(any());

    }

}