package ms.management.utils;


import ms.management.dtos.NewCourseDTO;
import ms.management.dtos.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
public class CustomKafkaSerializer implements Serializer<Message<NewCourseDTO>> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Message<NewCourseDTO> data) {
        try {
            if (data == null){
                log.warn("Null received at serializing");
                return new byte[0];
            }
            log.warn("Serializing...{}", data);
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing Message to byte[]");
        }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Message<NewCourseDTO> data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
