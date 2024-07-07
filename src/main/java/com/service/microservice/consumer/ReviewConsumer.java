package com.service.microservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReviewConsumer {


    @PulsarListener(
            topics = "${spring.pulsar.producer.topic-name2}",
            subscriptionName = "${spring.pulsar.consumer.subscription.name1}",
            subscriptionType = SubscriptionType.Shared
    )
    public boolean consumeReviewEvent(Boolean response) throws JsonProcessingException {

        log.info("EventConsumer:: consumeReviewEvent consumed events {}",response);
        return response;

    }
}
