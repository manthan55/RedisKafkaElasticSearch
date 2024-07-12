package com.manthan.rediskafkaelasticsearch.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        Map<String, Object> configs = new HashMap<>();
        // AWS
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "b-2.rkeskafka.83k8r8.c2.kafka.ap-south-1.amazonaws.com:9094,b-1.rkeskafka.83k8r8.c2.kafka.ap-south-1.amazonaws.com:9094");
        // DOCKER
//        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29092");
        // LOCAL
//        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");

        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 104857600);
        configs.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 104857600);
        configs.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        configs.put(ProducerConfig.ACKS_CONFIG, "all");
        configs.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        configs.put(ProducerConfig.RETRIES_CONFIG, 0);
        return new DefaultKafkaProducerFactory<>(configs);
    }
}
