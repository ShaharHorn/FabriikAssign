package com;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class ScoreConsumer {
    @Autowired
    CustomersScoresServiceImpl customersScoresService;

    @KafkaListener(topicPartitions
            = @TopicPartition(topic = "customers", partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0")}), groupId = "group_id")
    public void consumeMessage(ConsumerRecord<String, String> message){
        System.out.println("0    " + message.key() + "    " + message.value());
        customersScoresService.processMessage(message.key(),message.value());
    }
    @KafkaListener(topicPartitions
            = @TopicPartition(topic = "customers", partitionOffsets = {@PartitionOffset(partition = "1", initialOffset = "0")}), groupId = "group_id")
    public void consumeMessagePartOne(ConsumerRecord<String, String> message){
        System.out.println("1    " + message.key() + "    " + message.value());
        customersScoresService.processMessage(message.key(),message.value());
    }
    @KafkaListener(topicPartitions
            = @TopicPartition(topic = "customers", partitionOffsets = {@PartitionOffset(partition = "2", initialOffset = "0")}), groupId = "group_id")
    public void consumeMessagePartTwo(ConsumerRecord<String, String> message){
        System.out.println("2    " + message.key() + "    " + message.value());
        customersScoresService.processMessage(message.key(),message.value());
    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic("customers",3,(short)1);
    }
}
