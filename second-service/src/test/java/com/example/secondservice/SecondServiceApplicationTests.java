package com.example.secondservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.cloud.config.enabled=false")
class SecondServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
