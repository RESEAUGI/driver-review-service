package com.service.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("${average_ratings_table.name}")
public class AverageRating {
    @PrimaryKey
    @Column("${column.service_provider.id}")
    private UUID driverId;
    @Column("average_rating")
    private float averageRating;
    @Column("total_reviews")
    private int totalReviews;
    @Column("updated_at")
    private String updatedAt;
}