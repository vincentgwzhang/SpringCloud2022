package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.TransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class KafkaController {

    @Autowired
    private StreamBridge streamBridge;

    @PostMapping("sendMessage")
    public ResponseEntity<String> sendMessage() {
        TransferObject transferObject = new TransferObject();
        transferObject.setStr("str message");
        transferObject.setAge(12);
        transferObject.setVelocity(12.35f);
        transferObject.setTemperature(BigDecimal.valueOf(84L));
        transferObject.setNumber(52.3f);

        streamBridge.send("sendTransferObjectChannel-out-0", transferObject);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
