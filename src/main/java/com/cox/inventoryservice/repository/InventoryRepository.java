package com.cox.inventoryservice.repository;

import com.cox.inventoryservice.model.Inventory;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface InventoryRepository extends Neo4jRepository<Inventory, String> {

    Optional<Inventory> findByResourceId(String resourceId);
}
