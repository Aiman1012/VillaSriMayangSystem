package com.softwaredev.srimayangvilla.Repository;

import com.softwaredev.srimayangvilla.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Long countByPaymentId(Long roomId);
}
