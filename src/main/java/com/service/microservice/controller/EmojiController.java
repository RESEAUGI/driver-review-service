package com.service.microservice.controller;

import com.service.microservice.model.Emoji;
import com.service.microservice.service.EmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emojis")
public class EmojiController {
    @Autowired
    private EmojiService emojiService;

    /* @PostMapping
    public ResponseEntity<Emoji> updateEmojiByUserIdAndDriverId(@RequestBody Emoji updatedEmoji) {
        Emoji emoji = emojiService.findByCompleteId(updatedEmoji.getUserId(), updatedEmoji.getDriverId());
        if (emoji == null) {
            Emoji savedEmoji = emojiService.saveEmoji(emoji);
            return new ResponseEntity<>(savedEmoji, HttpStatus.CREATED);
        } else if (emoji.getEmojiName() == updatedEmoji.getEmojiName()) {
            emojiService.deleteByUserIdAndDriverId(updatedEmoji.getUserId(), updatedEmoji.getDriverId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        emoji.setEmojiName(updatedEmoji.getEmojiName());
        emojiService.saveEmoji(emoji);
        return new ResponseEntity<>(emoji, HttpStatus.ACCEPTED);
    } */
    @PutMapping
public ResponseEntity<Emoji> updateEmojiByUserIdAndDriverId(@RequestBody Emoji updatedEmoji) {
    Emoji emoji = emojiService.findByCompleteId(updatedEmoji.getUserId(), updatedEmoji.getDriverId());
    if (emoji == null) {
        Emoji savedEmoji = emojiService.saveEmoji(updatedEmoji);
        return new ResponseEntity<>(savedEmoji, HttpStatus.CREATED);
    } else if (emoji.getEmojiName().equals(updatedEmoji.getEmojiName())) {
        emojiService.deleteByUserIdAndDriverId(updatedEmoji.getUserId(), updatedEmoji.getDriverId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        emoji.setEmojiName(updatedEmoji.getEmojiName());
        emojiService.saveEmoji(emoji);
        return new ResponseEntity<>(emoji, HttpStatus.OK);
    }
}


    /*
     * @GetMapping("/user/{userId}")
     * public ResponseEntity<List<Emoji>> getEmojisByUserId(@PathVariable UUID
     * userId) {
     * List<Emoji> emojis = emojiService.findByUserId(userId);
     * return new ResponseEntity<>(emojis, HttpStatus.OK);
     * }
     * 
     * @GetMapping("/driver/{driverId}")
     * public ResponseEntity<List<Emoji>> getEmojisByDriverId(@PathVariable UUID
     * driverId) {
     * List<Emoji> emojis = emojiService.findByDriverId(driverId);
     * return new ResponseEntity<>(emojis, HttpStatus.OK);
     * }
     * 
     * @GetMapping("/driver/{userId}/{driverId}")
     * public ResponseEntity<Emoji> getEmojiByUserIdAndDriverId(@PathVariable UUID
     * userId,
     * 
     * @PathVariable UUID driverId) {
     * Emoji emoji = emojiService.findByCompleteId(userId, driverId);
     * return new ResponseEntity<>(emoji, HttpStatus.OK);
     * }
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
