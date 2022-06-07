package com.bustracker.config.kafka;

import com.bustracker.entity.TimeRow;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class KafkaTimeRowConsumerConfig {

//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServers;
//
//    @Value("${spring.kafka.consumer.group-id}")
//    private String groupId;
//
//    @Bean
//    public ConsumerFactory<String, TimeRow> timeConsumer() {
//
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//
//        return new DefaultKafkaConsumerFactory<>(
//                configs,
//                new StringDeserializer(),
//                new JsonDeserializer<>(TimeRow.class));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, TimeRow> timeRowListener() {
//        ConcurrentKafkaListenerContainerFactory<String, TimeRow> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(timeConsumer());
//        return factory;
//    }
}
