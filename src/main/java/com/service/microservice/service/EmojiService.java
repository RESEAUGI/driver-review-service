package com.service.microservice.service;

import com.service.microservice.model.Emoji;
import com.service.microservice.repository.EmojiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmojiService {
    @Autowired
    private EmojiRepository emojiRepository;

    public Emoji saveEmoji(Emoji emoji) {
        return emojiRepository.save(emoji);
    }

    public List<Emoji> findByUserId(UUID userId) {
        return emojiRepository.findByUserId(userId);
    }

    public List<Emoji> findByDriverId(UUID driverId) {
        return emojiRepository.findByDriverId(driverId);
    }

    public List<Emoji> findByEmojiName(String emojiName) {
        return emojiRepository.findByEmojiName(emojiName);
    }

    public Emoji findByCompleteId(UUID userId, UUID driverId) {
        return emojiRepository.findByCompleteId(userId,driverId);
    }

    public void deleteByUserIdAndDriverId(UUID userId, UUID driverId) {
        emojiRepository.deleteByUserIdAndDriverId(userId,driverId);
    }
}
