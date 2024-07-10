package com.service.microservice.repository;
import com.service.microservice.model.EmojiNumsByDriver;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmojiNumsByDriverRepository extends CassandraRepository<EmojiNumsByDriver, UUID> {
}
