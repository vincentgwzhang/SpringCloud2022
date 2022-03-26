package com.atguigu.springcloud.repository;

import com.atguigu.springcloud.entity.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {
}
