package com.bustracker.service.kafka;

import com.bustracker.entity.TimeRow;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaTimeConsumerService {

    @KafkaListener(topics = "testTopic", groupId = "group-id-test", containerFactory = "timeRowListener")
    public void consume(TimeRow timeRow) throws IOException {
        System.out.println("receive timeRow: " + timeRow);
    }
}
