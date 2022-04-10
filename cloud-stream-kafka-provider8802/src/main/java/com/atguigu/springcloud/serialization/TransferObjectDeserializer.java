package com.atguigu.springcloud.serialization;

import com.atguigu.springcloud.dto.TransferObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
public class TransferObjectDeserializer implements Deserializer<TransferObject> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TransferObject deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            log.info("Deserializing...");
            return objectMapper.readValue(new String(data, Charsets.UTF_8), TransferObject.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to TransferObject");
        }
    }

}
