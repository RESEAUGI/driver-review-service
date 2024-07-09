package com.service.microservice.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("emoji")
public class Emoji {
    private UUID userId;
    private UUID driverId;
    private String emojiName;
    public Emoji(UUID userId, UUID driverId, String emojiName) {
        this.userId = userId;
        this.driverId = driverId;
        this.emojiName = emojiName;
    }
    
    
}
