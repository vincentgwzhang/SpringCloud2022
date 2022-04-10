package com.atguigu.springcloud.serialization;

import com.atguigu.springcloud.dto.TransferObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class TransferObjectSerializer implements Serializer<TransferObject> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, TransferObject data) {
        try {
            if (data == null){
                System.out.println("Null received at serializing");
                return null;
            }
            log.info("Serializing...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing TransferObject to byte[]");
        }
    }

}
