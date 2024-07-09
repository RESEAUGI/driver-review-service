package com.service.microservice.controller;

import com.datastax.oss.driver.shaded.guava.common.base.Optional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.microservice.model.Emoji;
import com.service.microservice.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create")
    public ResponseEntity<Emoji> createReview(@RequestBody Emoji review) {
        Emoji createdReview = reviewService.createReview(review);
        return ResponseEntity.ok(createdReview);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Emoji>> getReviewsByDriver(@PathVariable UUID driverId) {
        List<Emoji> reviews = reviewService.getReviewsByDriver(driverId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<Emoji> getReviewByReservation(@PathVariable UUID reservationId) {
        Emoji review = reviewService.getReviewByReservation(reservationId);
        return review != null ? ResponseEntity.ok(review) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<Emoji> updateReview(@PathVariable UUID reviewId, @RequestHeader("User-Id") UUID userId,
            @RequestBody Emoji review) {
        Emoji updatedReview = reviewService.updateReview(reviewId, userId, review);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID reviewId, @RequestHeader("User-Id") UUID userId) {
        reviewService.deleteReview(reviewId, userId);
        return ResponseEntity.noContent().build();
    }
}