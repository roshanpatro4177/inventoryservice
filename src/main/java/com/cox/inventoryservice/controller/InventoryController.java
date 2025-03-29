package com.cox.inventoryservice.controller;

import com.cox.inventoryservice.model.Inventory;
import com.cox.inventoryservice.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<Inventory> checkAvailability(@PathVariable String resourceId) {
        Optional<Inventory> inventory = inventoryService.checkAvailability(resourceId);
        return inventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{resourceId}/assign")
    public ResponseEntity<Inventory> assignResource(@PathVariable String resourceId) {
        Optional<Inventory> inventory = inventoryService.assignResource(resourceId);
        return inventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(409).build());
    }
}