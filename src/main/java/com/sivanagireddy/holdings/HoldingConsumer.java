package com.sivanagireddy.holdings;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

//@Component
//@Slf4j
public class HoldingConsumer implements MessageListener<String, HoldingRecord> {

    @Override
    public void onMessage(ConsumerRecord<String, HoldingRecord> record) {
//        log.info("Message received : {}", record.value());
    }
}
