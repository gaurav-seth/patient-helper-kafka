package com.patient.helper.doctor.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.patient.helper.doctor.model.DoctorAssignedEvent;
import com.patient.helper.doctor.policy.DoctorAssignmentPolicy;
import com.patient.helper.doctor.policy.PolicyResolver;
import com.patient.helper.doctor.service.KafkaDoctorProducerService;
import com.patient.helper.events.PatientRegisteredEvent;

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
	
	@Autowired
	private PolicyResolver policyResolver;

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
		
		DoctorAssignmentPolicy policy = policyResolver.resolve(event.getPolicyVersion().toString());

		DoctorAssignedEvent doctorAssignedEvent = policy.assign(event);
		
		// ---- Publish next event ----
		producerService.sendDoctorAssignedEvent(doctorAssignedEvent);

		System.out.println("Policy: " + event.getPolicyVersion() + ", Doctor: " + doctorAssignedEvent.getDoctorId() + 
				" assigned to patient: " + event.getName());
	}
}
