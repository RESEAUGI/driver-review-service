package com.service.microservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.microservice.model.DriverReview;
import com.service.microservice.service.ReviewService;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.apache.pulsar.common.schema.SchemaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReviewConsumer {
    private final ReviewService reviewService;


    public ReviewConsumer(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PulsarListener(
            topics = "${spring.pulsar.producer.topic-name2}",
            subscriptionName = "${spring.pulsar.consumer.subscription.name1}",
            subscriptionType = SubscriptionType.Shared,
            schemaType = SchemaType.JSON
    )
    public void consumeReviewEvent(DriverReview review) throws JsonProcessingException {

        log.info("EventConsumer:: consumeReviewEvent consumed events {}",review);
        if(review != null){
            log.info("Eventconsumer:: consumeTextEvent consumed events {} ",
                new ObjectMapper().writeValueAsString(review));
            reviewService.createReview(review);
            
        }   else {
            log.info("L'utilisateur n'existe pas");
        }

    }
}
