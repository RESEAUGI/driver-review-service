package com.service.microservice.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("emoji")
public class Emoji {
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID userId;

    @PrimaryKeyColumn(name = "driver_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID driverId;
    @Column("emoji_name")
    private String emojiName;

    public Emoji(UUID userId, UUID driverId, String emojiName) {
        this.userId = userId;
        this.driverId = driverId;
        this.emojiName = emojiName;
    }
}