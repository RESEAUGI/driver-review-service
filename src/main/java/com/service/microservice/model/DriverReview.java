package com.service.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("${review_table.name}")
public class DriverReview {
    @PrimaryKey
    private UUID reviewId;
    @Column("${column.reviewer.id}")
    private UUID userId;
    @Column("${column.service_provider.id}")
    private UUID driverId;
    private UUID reservationId;
    @Column("${column.reviewer.name}")
    private String userName;
    private String comment;
    private String createdAt;
    private String updatedAt;
    private int likes;
    private int dislikes;
    private int note;
    private String icon;
}
