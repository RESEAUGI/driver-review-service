package com.service.microservice.model;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("${emojiNums_table.name}")
public class EmojiNumsByDrive
 {
    @PrimaryKey
    @Column("${column.service_provider.id}")
    private UUID driverId;
    private BigInteger handUp;
    private BigInteger handDown;
    private BigInteger heart;
    private BigInteger angry;
    private int totalReviews;
}