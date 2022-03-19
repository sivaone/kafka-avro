package com.sivanagireddy.holdings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

//@Configuration
public class HoldingsListenerConfig {

//    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, HoldingRecord>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, HoldingRecord> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setContainerCustomizer(container -> {
            container.setConcurrency(3);
            container.setupMessageListener(messageListener());
        });
        return factory;
    }

//    @Bean
//    @Scope("prototype")
    public MessageListener<String, HoldingRecord> messageListener() {
        return new HoldingConsumer();
    }
}
