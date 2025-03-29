package com.cox.inventoryservice.service;

import com.cox.inventoryservice.kafka.InventoryProducer;
import com.cox.inventoryservice.model.Inventory;
import com.cox.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryProducer inventoryProducer;

    public InventoryService(InventoryRepository inventoryRepository, InventoryProducer inventoryProducer) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryProducer = inventoryProducer;
    }

    public Optional<Inventory> checkAvailability(String resourceId) {
        return inventoryRepository.findByResourceId(resourceId);
    }

    public Optional<Inventory> assignResource(String resourceId) {
        return inventoryRepository.findByResourceId(resourceId).map(inventory -> {
            if (inventory.isAvailable()) {
                inventory.setAvailable(false);
                Inventory updatedInventory = inventoryRepository.save(inventory);
                inventoryProducer.sendInventoryEvent(updatedInventory);
                return updatedInventory;
            }
            return null;
        });
    }
}
