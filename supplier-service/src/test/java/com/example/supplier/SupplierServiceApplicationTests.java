package com.example.supplier;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.cloud.config.enabled=false")
class SupplierServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
