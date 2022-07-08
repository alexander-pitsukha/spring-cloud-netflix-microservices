package com.example.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @PostMapping
    ResponseEntity<HttpStatus> placeOrder(@RequestBody Order order) {
        log.info("Placing an order for product: {} with quantity: {}", order.getProductName(), order.getQuantity());
        return ResponseEntity.ok().build();
    }
}