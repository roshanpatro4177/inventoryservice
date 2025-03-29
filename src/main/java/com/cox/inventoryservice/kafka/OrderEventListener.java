package com.cox.inventoryservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {

    @KafkaListener(topics = "order-topic", groupId = "inventory-service-group")
    public void listenOrderEvent(String orderId) {
        System.out.println("Received new order: " + orderId);
        // Logic for processing inventory allocation when an order is placed
    }
}