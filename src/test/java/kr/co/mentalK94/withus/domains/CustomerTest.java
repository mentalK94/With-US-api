package kr.co.mentalK94.withus.domains;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    @DisplayName("Customer 객체 생성 테스트")
    public void createTest() {
        Customer customer = Customer.builder()
                .name("김한솔")
                .email("doingnow94@gmail.com")
                .build();

        assertEquals(customer.getName(), "김한솔");
    }
}