package com.sivanagireddy.holdings;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class HoldingsProducer {

    private final KafkaTemplate<String, HoldingRecord> holdingsProducer;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @EventListener(ApplicationStartedEvent.class)
    public void produceHoldings() {

        HoldingRecord record = HoldingRecord.newBuilder()
                .setHoldingId(System.currentTimeMillis())
                .setType("Plot")
                .setValue(92345.00)
                .setCreatedDate(System.currentTimeMillis())
                .setAddress("99 Main Road, Hyderabad, IN")
                .build();

        holdingsProducer.send(topic, record)
                .addCallback(
                        result -> {
                            if (Objects.nonNull(result)) {
                                log.info("Message published at : {}", result.getRecordMetadata());
                            }
                        },
                        ex -> log.error("Failed to publish message", ex)
                );

        holdingsProducer.flush();
        log.info("Message published -- end");
    }

}
