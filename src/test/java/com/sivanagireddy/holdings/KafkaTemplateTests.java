package com.sivanagireddy.holdings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 3, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class KafkaTemplateTests {

    @Autowired
    private HoldingsProducer holdingsProducer;

    @Test
    public void testProducer(EmbeddedKafkaBroker broker) {
        holdingsProducer.produceHoldings();
    }
}
