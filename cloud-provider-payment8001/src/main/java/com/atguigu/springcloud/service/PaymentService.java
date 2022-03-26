package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dto.PaymentDTO;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.mapper.PaymentMapper;
import com.atguigu.springcloud.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    private PaymentMapper paymentMapper;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.toEntity(paymentDTO);
        payment = paymentRepository.save(payment);
        return paymentMapper.toDTO(payment);
    }

    public PaymentDTO getPaymentById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.map(paymentMapper::toDTO).orElse(null);
    }

}
