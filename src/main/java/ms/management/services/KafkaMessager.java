package ms.management.services;

import ms.management.dtos.CorrelationId;
import ms.management.dtos.NewCourseDTO;
import ms.management.dtos.Message;
import ms.management.entities.CoursesEntity;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class KafkaMessager {

    private final KafkaTemplate<String, Message<NewCourseDTO>> kafkaTemplate;

    public void sendNewCourseMessage(CoursesEntity data) {
        log.info("C=KafkaMessager::M=sendNewCourseMessage::I={}", data);

        String instructorId = data.getInstructorId().toString();
        final String correlationId = new CorrelationId().toString();

        log.warn("C=KafkaMessager::M=sendNewCourseMessage::I={}", correlationId);
        Message<NewCourseDTO> message = new Message<>(correlationId, new NewCourseDTO(data.getTitle(), data.getCategory(), instructorId));

        final ProducerRecord<String, Message<NewCourseDTO>> newRecord =
            new ProducerRecord<>("MS_MANAGEMENT_EVENT_NEW_COURSE", generateTopicKey(instructorId), message);

        log.info("C=KafkaMessager::M=sendNewCourseMessage::O={}", newRecord);

        kafkaTemplate.send(newRecord);
    }

    private String generateTopicKey(String instructorId) {
        return "MS_MANAGEMENT::INSTRUCTOR::" + instructorId;
    }
}
