package com.service.microservice.service;

import com.service.microservice.exceptions.ResourceNotFoundException;
import com.service.microservice.exceptions.UnauthorizedException;
import com.service.microservice.model.Emoji;
import com.service.microservice.model.EmojiNums;
import com.service.microservice.repository.EmojiRepository;
import com.service.microservice.utils.CassandraIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EmojiService {
    private final EmojiRepository EmojiRepository;
    private final CassandraIdGenerator cassandraIdGenerator;

    @Autowired
    public EmojiService(EmojiRepository emojiRepository,
            CassandraIdGenerator cassandraIdGenerator) {
        this.EmojiRepository = emojiRepository;
        this.cassandraIdGenerator = cassandraIdGenerator;
    }

    public Emoji createEmoji(Emoji emoji) {

        // Générer un ID unique pour la emoji
        UUID emojiId = cassandraIdGenerator.getNextId();
        emoji.setEmojiId(emojiId);

        // Sauvegarder l'emoji
        Emoji savedEmoji = EmojiRepository.save(emoji);

        updateEmojiNums(emoji.getEmojiId());

        return savedEmoji;
    }

    private void updateEmojiNums(UUID driverId) {
        List<Emoji> emojis = EmojiRepository.findByDriverId(driverId);
        BigDecimal averageRating = calculateEmojiNums(emojis);
        int totalEmojis = emojis.size();

        EmojiNums emojiNums = new EmojiNums();
        avgRating.setDriverId(driverId);
        avgRating.setAverageRating(averageRating);
        avgRating.setTotalEmojis(totalEmojis);
        avgRating.setUpdatedAt(LocalDateTime.now().toString());

        averageRatingRepository.save(avgRating);
    }

    private BigDecimal calculateEmojiNums(List<Emoji> emojis) {
        // Implémenter la logique de calcul du score moyen
        return BigDecimal.valueOf(0);
    }

    public List<Emoji> getEmojisByDriver(UUID driverId) {
        return EmojiRepository.findByDriverId(driverId);
    }

    public Emoji getEmojiByReservation(UUID reservationId) {
        return EmojiRepository.findByReservationId(reservationId);
    }

    public Emoji updateEmoji(UUID emojiId, UUID userId, Emoji updatedEmoji) {
        Emoji existingEmoji = EmojiRepository.findById(emojiId)
                .orElseThrow(() -> new ResourceNotFoundException("Emoji not found"));

        if (!existingEmoji.getUserId().equals(userId)) {
            throw new UnauthorizedException("You are not authorized to update this emoji");
        }
        // Mise à jour des champs modifiables
        existingEmoji.setComment(updatedEmoji.getComment());
        existingEmoji.setNote(updatedEmoji.getNote());
        existingEmoji.setUpdatedAt(LocalDateTime.now().toString());
        existingEmoji.setIcon(updatedEmoji.getIcon());

        Emoji savedEmoji = EmojiRepository.save(existingEmoji);

        // Mettre à jour la moyenne des notes
        updateAverageRating(existingEmoji.getDriverId());

        return savedEmoji;
    }

    public void deleteEmoji(UUID emojiId, UUID driverId) {
        Emoji emoji = EmojiRepository.findById(emojiId)
                .orElseThrow(() -> new ResourceNotFoundException("Emoji not found"));

        if (!emoji.getUserId().equals(userId)) {
            throw new UnauthorizedException("You are not authorized to delete this emoji");
        }
        EmojiRepository.delete(emoji);

        // Mettre à jour la moyenne des notes
        updateAverageRating(emoji.getDriverId());
    }

}