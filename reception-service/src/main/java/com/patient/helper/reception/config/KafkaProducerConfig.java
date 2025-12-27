package com.patient.helper.reception.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.patient.helper.events.PatientRegisteredEvent;

/**
 * KafkaProducerConfig
 *
 * This configuration class sets up the Kafka Producer components required
 * for sending messages to a Kafka topic. It defines:
 *
 * 1. ProducerFactory → Responsible for creating Kafka producers
 * 2. KafkaTemplate   → High-level helper used by services to publish events
 *
 * The producer uses:
 * - String keys (patientId)
 * - JSON values (PatientRegisteredEvent)
 *
 * Configuration here ensures all producer instances use the same
 * bootstrap server and serialization settings.
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * Creates a ProducerFactory that defines how Kafka producers should be created.
     * It sets the Kafka server address and the serializers used for keys and values.
     *
     * @return ProducerFactory configured to produce PatientRegisteredEvent messages
     */
    @Bean
    public ProducerFactory<String, PatientRegisteredEvent> producerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        config.put(
        	    "schema.registry.url",
        	    "http://localhost:8085"
        	);


        return new DefaultKafkaProducerFactory<>(config);
    }

    /**
     * KafkaTemplate is a high-level abstraction that allows sending messages
     * to Kafka topics without manually creating producer instances.
     *
     * @return KafkaTemplate for sending PatientRegisteredEvent messages
     */
    @Bean
    public KafkaTemplate<String, PatientRegisteredEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
