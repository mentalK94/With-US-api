package kr.co.mentalK94.withus.interfaces;

import kr.co.mentalK94.withus.applications.PurchaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PurchaseController.class)
class PurchaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PurchaseService purchaseService;


    @Test
    public void create() throws Exception {

        mvc.perform(post("/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"totalPrice\":10000, \"usingPoint\":1500, \"payAmount\":8500, " +
                        "\"shippingAddress\":\"삼덕로 63번길 32, 101동 1101호\", " +
                        "\"shippingMemo\":\"문 앞에 놔주세요.\", " +
                        "\"phone\":\"010-1234-9874\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/purchase/1"));

        verify(purchaseService).addPurchase(any());
    }
}