package com.service.microservice.service;

import com.service.microservice.model.Emoji;
import com.service.microservice.model.EmojiNumsByDriver;
import com.service.microservice.repository.EmojiNumsByDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmojiNumsByDriverService {
    @Autowired
    private EmojiNumsByDriverRepository emojiNumsByDriverRepository;

    public List<EmojiNumsByDriver> findAll() {
        return emojiNumsByDriverRepository.findAll();
    }

    public Optional<EmojiNumsByDriver> findById(UUID driverId) {
        return emojiNumsByDriverRepository.findById(driverId);
    }

    public EmojiNumsByDriver save(EmojiNumsByDriver emojiNumsByDriver) {
        return emojiNumsByDriverRepository.save(emojiNumsByDriver);
    }

    public void deleteById(UUID driverId) {
        emojiNumsByDriverRepository.deleteById(driverId);
    }

    public EmojiNumsByDriver update(UUID driverId, BigInteger handUp, BigInteger handDown, BigInteger heart,
            BigInteger angry, int totalReviews) {
        Optional<EmojiNumsByDriver> optionalEmojiNumsByDriver = emojiNumsByDriverRepository.findById(driverId);
        if (optionalEmojiNumsByDriver.isPresent()) {
            EmojiNumsByDriver emojiNumsByDriver = optionalEmojiNumsByDriver.get();
            emojiNumsByDriver.setHandUp(handUp);
            emojiNumsByDriver.setHandDown(handDown);
            emojiNumsByDriver.setHeart(heart);
            emojiNumsByDriver.setAngry(angry);
            emojiNumsByDriver.setTotalReviews(totalReviews);
            return emojiNumsByDriverRepository.save(emojiNumsByDriver);
        } else {
            return null;
        }
    }

    public EmojiNumsByDriver calculateDriverEmojiNums(List<Emoji> emojis) {
        EmojiNumsByDriver emojiNumsByDriver = new EmojiNumsByDriver(emojis.get(0).getDriverId(), BigInteger.valueOf(0),
                BigInteger.valueOf(0), BigInteger.valueOf(0), BigInteger.valueOf(0), 0);

        for (Emoji emoji : emojis) {
            emojiNumsByDriver.setTotalReviews(emojiNumsByDriver.getTotalReviews() + 1);
            System.out.println("ici..." + emoji.getEmojiName());
            /*
             * if (emoji.getEmojiName() == "hand-up") {
             * emojiNumsByDriver.setHandUp(emojiNumsByDriver.getHandUp().add(BigInteger.
             * valueOf(1)));
             * } else if (emoji.getEmojiName() == "hand-down") {
             * emojiNumsByDriver.setHandDown(emojiNumsByDriver.getHandDown().add(BigInteger.
             * valueOf(1)));
             * 
             * } else if (emoji.getEmojiName() == "heart") {
             * System.out.println("Ici");
             * emojiNumsByDriver.setHeart(emojiNumsByDriver.getHeart().add(BigInteger.
             * valueOf(1)));
             * 
             * } else {
             * emojiNumsByDriver.setAngry(emojiNumsByDriver.getAngry().add(BigInteger.
             * valueOf(1)));
             * 
             * }
             */
            switch (emoji.getEmojiName()) {
                case "hand-up":
                    emojiNumsByDriver.setHandUp(emojiNumsByDriver.getHandUp().add(BigInteger.valueOf(1)));
                    break;
                case "hand-down":
                    emojiNumsByDriver.setHandDown(emojiNumsByDriver.getHandDown().add(BigInteger.valueOf(1)));
                    break;
                case "heart":
                    System.out.println("Ici");
                    emojiNumsByDriver.setHeart(emojiNumsByDriver.getHeart().add(BigInteger.valueOf(1)));
                    break;
                case "angry":
                    emojiNumsByDriver.setAngry(emojiNumsByDriver.getAngry().add(BigInteger.valueOf(1)));
                    break;
                default:
                    System.out.println("C'est pas bon du tout");
                    break;
            }
            System.out.println("Enum drivers. " + emojiNumsByDriver);
        }
        return emojiNumsByDriver;
    }

}
