package com.atguigu.springcloud.dto;

import com.atguigu.springcloud.validation.SerialConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;

    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be blank")
    @SerialConstraint(message = "Error serial value")
    private String serial;

}
