package com.service.microservice.model;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("emoji_nums")
@AllArgsConstructor
@NoArgsConstructor
public class EmojiNums {
    @PrimaryKey
    private UUID driverId;
    private BigDecimal handUp;
    private BigDecimal handDown;
    private BigDecimal heart;
    private BigDecimal angry;
    private int totalReviews;
    private String updatedAt;
    
}
