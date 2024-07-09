import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.service.microservice.model.Emoji;

import java.util.UUID;

@Repository
public interface EmojiRepository extends CassandraRepository<Emoji, UUID> {
    Emoji findByEmojiId(UUID emojiId);
    Emoji findByUserId(UUID userId);
    Emoji findByDriverId(UUID driverId);
    Emoji findByReviewId(UUID reviewId);
    Emoji findByEmojiName(String emojiName);
}
