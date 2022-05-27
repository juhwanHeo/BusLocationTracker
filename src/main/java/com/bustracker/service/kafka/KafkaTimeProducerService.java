package com.bustracker.service.kafka;

import com.bustracker.entity.TimeRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaTimeProducerService {

    private final KafkaTemplate<String, TimeRow> kafkaTemplate;

    @Autowired
    public KafkaTimeProducerService(KafkaTemplate<String, TimeRow> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTimeRow(TimeRow timeRow)  {
        kafkaTemplate.send("testTopic", timeRow);
    }

}
