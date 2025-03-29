package com.cox.inventoryservice.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node("Resource")
public class Inventory {

    @Id
    private String resourceId;
    private String type;
    private boolean available;
}
