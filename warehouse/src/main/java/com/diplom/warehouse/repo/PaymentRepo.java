package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
