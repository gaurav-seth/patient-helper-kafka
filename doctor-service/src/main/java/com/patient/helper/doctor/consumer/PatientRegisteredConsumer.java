package com.patient.helper.doctor.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.patient.helper.doctor.model.DoctorAssignedEvent;
import com.patient.helper.doctor.model.PatientRegisteredEvent;
import com.patient.helper.doctor.service.KafkaDoctorProducerService;

/**
 * PatientRegisteredConsumer
 *
 * This Kafka consumer listens to the "patient.registered" topic
 * and processes patient registration events.
 *
 * Responsibilities:
 *  - Consume messages from ALL partitions of the topic
 *  - Deserialize JSON into PatientRegisteredEvent
 *  - Trigger doctor assignment logic (next step)
 *
 * Kafka guarantees:
 *  - One message is processed by only one consumer in the group
 *  - Order is preserved per partition
 */
@Service
public class PatientRegisteredConsumer {

	@Autowired
	private KafkaDoctorProducerService producerService;

	/**
	 * Kafka listener method that consumes patient registration events.
	 *
	 * @param record Kafka ConsumerRecord containing key, value, partition, offset
	 */
	@KafkaListener(
	        topics = "patient.registered",
	        groupId = "doctor-service-group",
	        containerFactory = "kafkaListenerContainerFactory"
	)
	public void consumePatientRegisteredEvent(
			ConsumerRecord<String, PatientRegisteredEvent> record) {

		PatientRegisteredEvent event = record.value();
		
		System.out.println("Consumed patient.registered event");
	    System.out.println("Key: " + record.key());
	    System.out.println("Partition: " + record.partition());
	    System.out.println("Offset: " + record.offset());
	    System.out.println("Event: " + event);

		System.out.println("Consumed patient.registered event for patientId: " + event.getPatientId());

		// ---- Doctor assignment logic (simple) ----
		DoctorAssignedEvent doctorAssignedEvent =
				new DoctorAssignedEvent(
						event.getPatientId(),
						"D-101",
						"Dr. Rajesh Verma",
						"General Physician",
						System.currentTimeMillis()
						);

		// ---- Publish next event ----
		producerService.sendDoctorAssignedEvent(doctorAssignedEvent);

		System.out.println("Published doctor.assigned event for patientId: " + event.getPatientId());
	}
}
