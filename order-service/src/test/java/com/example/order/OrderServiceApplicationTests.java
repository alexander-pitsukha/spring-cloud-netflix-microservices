package com.example.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.cloud.config.enabled=false")
class OrderServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
