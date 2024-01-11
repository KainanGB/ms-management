package ms.management.configurations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {
    @Bean
    public NewTopic newCourseTopic() {
        return TopicBuilder.name("MS_MANAGEMENT_EVENT_NEW_COURSE")
            .partitions(2)
            .replicas(3).build();
    }

}