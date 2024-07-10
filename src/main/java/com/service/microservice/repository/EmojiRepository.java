package com.service.microservice.repository;

import com.service.microservice.model.Emoji;

import org.springframework.data.cassandra.core.mapping.Embedded.Nullable;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmojiRepository extends CassandraRepository<Emoji, UUID> {
    @AllowFiltering
    List<Emoji> findByUserId(UUID userId);
    @AllowFiltering
    List<Emoji> findByDriverId(UUID driverId);
    @AllowFiltering
    List<Emoji> findByEmojiName(String emojiName);
    @AllowFiltering
    @Query("DELETE FROM emoji WHERE user_id = ?0 AND driver_id = ?1;")
    void deleteByUserIdAndDriverId(UUID user_id,UUID driver_id);
    @AllowFiltering
    @Query("SELECT * FROM emoji WHERE user_id = ?0 AND driver_id = ?1;")
    @Nullable
    Emoji findByCompleteId(UUID user_id,UUID driver_id);
}
