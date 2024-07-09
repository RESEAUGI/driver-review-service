import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.service.microservice.model.EmojiNums;

import java.util.UUID;

@Repository
public interface EmojiNumsRepository extends CassandraRepository<EmojiNums, UUID> {
}
