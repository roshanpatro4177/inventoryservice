package com.cox.inventoryservice.kafka;

import com.cox.inventoryservice.model.Inventory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryProducer {

    private final KafkaTemplate<String, Inventory> kafkaTemplate;

    public InventoryProducer(KafkaTemplate<String, Inventory> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInventoryEvent(Inventory inventory) {
        kafkaTemplate.send("inventory-topic", inventory.getResourceId(), inventory);
    }
}
