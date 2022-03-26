package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.CommonResult;
import com.atguigu.springcloud.dto.PaymentDTO;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("payment")
@Validated
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "create")
    public CommonResult<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO result = paymentService.createPayment(paymentDTO);
        return new CommonResult<>(HttpStatus.OK.value(), "Create Success", result);
    }

    @GetMapping(value = "{id}")
    public CommonResult<PaymentDTO> getPaymentById(@PathVariable("id") Long id) {
        PaymentDTO result = paymentService.getPaymentById(id);
        if (Objects.nonNull(result)) {
            return new CommonResult<>(HttpStatus.OK.value(), "Payment retrieve success", result);
        } else {
            return new CommonResult<>(HttpStatus.NOT_FOUND.value(), "Payment not found");
        }
    }
}
