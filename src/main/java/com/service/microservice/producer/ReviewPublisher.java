package com.service.microservice.producer;

import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

public class ReviewPublisher {
    private String topicName;

    private PulsarTemplate<Object> template;

    public ReviewPublisher(@Value("${spring.pulsar.producer.topic-name1}") String topicName,
            PulsarTemplate<Object> template) {
        this.topicName = topicName;
        this.template = template;
    }


    public void publishRawMessage(Review customer) {
        template.send(topicName, customer);
        System.out.println("sending message to the topic" + topicName2);
        log.info("EventPublisher::publishRawMessage publish the event {}", customer.toString());
    }

}
