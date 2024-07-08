package com.service.microservice.producer;

import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.service.microservice.model.DriverReview;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReviewPublisher {
    private String topicName;

    private PulsarTemplate<Object> template;
    

    public ReviewPublisher(@Value("${spring.pulsar.producer.topic-name1}") String topicName,
            PulsarTemplate<Object> template) {
        this.topicName = topicName;
        this.template = template;
    }


    public void publishRawMessage(DriverReview review) {
        template.send(topicName, review);
        log.info("EventPublisher::publishRawMessage publish the event {}",review.toString());
    }

}
