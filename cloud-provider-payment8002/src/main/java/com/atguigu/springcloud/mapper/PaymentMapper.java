package com.atguigu.springcloud.mapper;

import com.atguigu.springcloud.dto.PaymentDTO;
import com.atguigu.springcloud.entity.Payment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @BeanMapping (nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    PaymentDTO toDTO(Payment payment);

    @BeanMapping (nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    Payment toEntity(PaymentDTO paymentDTO);

}
