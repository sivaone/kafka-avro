package com.sivanagireddy.holdings;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HoldingListener {

    @KafkaListener(topics = "${spring.kafka.template.default-topic}", concurrency = "3")
    public void listen(final ConsumerRecord<String, HoldingRecord> record) {
        log.info("Message received : {}", record.value());
    }
}
