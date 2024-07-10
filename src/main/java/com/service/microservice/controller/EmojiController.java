package com.service.microservice.controller;

import com.service.microservice.model.Emoji;
import com.service.microservice.model.EmojiNumsByDriver;
import com.service.microservice.service.EmojiNumsByDriverService;
import com.service.microservice.service.EmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
        RequestMethod.OPTIONS }, maxAge = 3600)
@RestController
@RequestMapping("/emojis")
public class EmojiController {
    @Autowired
    private EmojiService emojiService;

    @Autowired
    private EmojiNumsByDriverService emojiNumsByDriverService;

    /*
     * @PostMapping
     * public ResponseEntity<Emoji> updateEmojiByUserIdAndDriverId(@RequestBody
     * Emoji updatedEmoji) {
     * Emoji emoji = emojiService.findByCompleteId(updatedEmoji.getUserId(),
     * updatedEmoji.getDriverId());
     * if (emoji == null) {
     * Emoji savedEmoji = emojiService.saveEmoji(emoji);
     * return new ResponseEntity<>(savedEmoji, HttpStatus.CREATED);
     * } else if (emoji.getEmojiName() == updatedEmoji.getEmojiName()) {
     * emojiService.deleteByUserIdAndDriverId(updatedEmoji.getUserId(),
     * updatedEmoji.getDriverId());
     * return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     * }
     * emoji.setEmojiName(updatedEmoji.getEmojiName());
     * emojiService.saveEmoji(emoji);
     * return new ResponseEntity<>(emoji, HttpStatus.ACCEPTED);
     * }
     */
    @PutMapping
    public ResponseEntity<Emoji> updateEmojiByUserIdAndDriverId(@RequestBody Emoji updatedEmoji) {
        /*
         * EmojiNumsByDriver emojiNumsByDriver = new
         * EmojiNumsByDriver(updatedEmoji.getDriverId(), BigInteger.valueOf(0),
         * BigInteger.valueOf(0), BigInteger.valueOf(0), BigInteger.valueOf(0), 0);
         */
        Emoji emoji = emojiService.findByCompleteId(updatedEmoji.getUserId(), updatedEmoji.getDriverId());
        System.out.println("id du driver: "+updatedEmoji.getDriverId());
        System.out.println("id de l'user: "+updatedEmoji.getUserId());
        List<Emoji> allDriverEmojis = emojiService.findByDriverId(updatedEmoji.getDriverId());
        if (emoji == null) {
            Emoji savedEmoji = emojiService.saveEmoji(updatedEmoji);
            allDriverEmojis = emojiService.findByDriverId(updatedEmoji.getDriverId());
            EmojiNumsByDriver emojiNumsByDriver = emojiNumsByDriverService.calculateDriverEmojiNums(allDriverEmojis);
            emojiNumsByDriverService.save(emojiNumsByDriver);
            return new ResponseEntity<>(savedEmoji, HttpStatus.CREATED);
        } else if (emoji.getEmojiName().equals(updatedEmoji.getEmojiName())) {
            emojiService.deleteByUserIdAndDriverId(updatedEmoji.getUserId(), updatedEmoji.getDriverId());
            allDriverEmojis = emojiService.findByDriverId(updatedEmoji.getDriverId());
            EmojiNumsByDriver emojiNumsByDriver = emojiNumsByDriverService.calculateDriverEmojiNums(allDriverEmojis);
            emojiNumsByDriverService.save(emojiNumsByDriver);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            emoji.setEmojiName(updatedEmoji.getEmojiName());
            emojiService.saveEmoji(emoji);
            allDriverEmojis = emojiService.findByDriverId(updatedEmoji.getDriverId());
            EmojiNumsByDriver emojiNumsByDriver = emojiNumsByDriverService.calculateDriverEmojiNums(allDriverEmojis);
            emojiNumsByDriverService.update(emojiNumsByDriver.getDriverId(), emojiNumsByDriver.getHandUp(),
                    emojiNumsByDriver.getHandDown(), emojiNumsByDriver.getHeart(), emojiNumsByDriver.getAngry(),
                    emojiNumsByDriver.getTotalReviews());
            return new ResponseEntity<>(emoji, HttpStatus.OK);
        }
    }

    /*
     * @GetMapping("/driver/{userId}/{driverId}")
     * public ResponseEntity<Emoji> getEmojiByUserIdAndDriverId(@PathVariable UUID
     * userId, @PathVariable UUID driverId) {
     * Emoji emoji = emojiService.findByCompleteId(userId, driverId);
     * return new ResponseEntity<>(emoji, HttpStatus.OK);
     * }
     * 
     * @GetMapping("/driver/{driverId}")
     * public ResponseEntity<List<Emoji>> getEmojisByDriverId(@PathVariable UUID
     * driverId) {
     * List<Emoji> emojis = emojiService.findByDriverId(driverId);
     * return new ResponseEntity<>(emojis, HttpStatus.OK);
     * }
     */
    @GetMapping("{driverId}")
    public ResponseEntity<Optional<EmojiNumsByDriver>> getEmojisByDriverId(@PathVariable UUID driverId) {
        Optional<EmojiNumsByDriver> emojis = emojiNumsByDriverService.findById(driverId);
        return new ResponseEntity<>(emojis, HttpStatus.OK);
    }
    /*
     * @GetMapping("/user/{userId}")
     * public ResponseEntity<List<Emoji>> getEmojisByUserId(@PathVariable UUID
     * userId) {
     * List<Emoji> emojis = emojiService.findByUserId(userId);
     * return new ResponseEntity<>(emojis, HttpStatus.OK);
     * }
     * 
     * 
     * 
     * @GetMapping("/name/{emojiName}")
     * public ResponseEntity<List<Emoji>> getEmojisByEmojiName(@PathVariable String
     * emojiName) {
     * List<Emoji> emojis = emojiService.findByEmojiName(emojiName);
     * return new ResponseEntity<>(emojis, HttpStatus.OK);
     * }
     * 
     * @DeleteMapping("/{userId}/{driverId}")
     * public ResponseEntity<Void> deleteEmoji(@PathVariable UUID
     * userId, @PathVariable UUID driverId) {
     * emojiService.deleteByUserIdAndDriverId(userId, driverId);
     * return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     * }
     */
}
