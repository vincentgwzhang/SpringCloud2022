package com.atguigu.springcloud.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferObject {

    private String str;

    private double number;

    private int age;

    private float velocity;

    private BigDecimal temperature;

}
