import com.service.microservice.model.EmojiNums;
import com.service.microservice.repository.EmojiNumsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmojiNumsService {

    private final EmojiNumsRepository emojiNumsRepository;

    @Autowired
    public EmojiNumsService(EmojiNumsRepository emojiNumsRepository) {
        this.emojiNumsRepository = emojiNumsRepository;
    }

    public Optional<EmojiNums> findByDriverId(UUID driverId) {
        return emojiNumsRepository.findById(driverId);
    }

    public EmojiNums save(EmojiNums emojiNums) {
        return emojiNumsRepository.save(emojiNums);
    }

    public void deleteByDriverId(UUID driverId) {
        emojiNumsRepository.deleteById(driverId);
    }

    public EmojiNums updateEmojiNums(UUID driverId, BigDecimal handUp, BigDecimal handDown, BigDecimal heart, BigDecimal angry) {
        Optional<EmojiNums> optionalEmojiNums = emojiNumsRepository.findById(driverId);
        if (optionalEmojiNums.isPresent()) {
            EmojiNums emojiNums = optionalEmojiNums.get();
            emojiNums.setHandUp(handUp);
            emojiNums.setHandDown(handDown);
            emojiNums.setHeart(heart);
            emojiNums.setAngry(angry);
            emojiNums.setTotalReviews(emojiNums.getTotalReviews() + 1);
            return emojiNumsRepository.save(emojiNums);
        } else {
            throw new RuntimeException("EmojiNums not found for driverId: " + driverId);
        }
    }
}
