package com.service.microservice.model;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("emoji_nums")
public class EmojiNumsByDriver {
    @PrimaryKey
    private UUID driverId;
    private BigInteger handUp;
    private BigInteger handDown;
    private BigInteger heart;
    private BigInteger angry;
    private int totalReviews;
}