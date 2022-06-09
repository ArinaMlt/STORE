package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Expenditure;
import com.diplom.warehouse.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CheckRepo extends JpaRepository<Expenditure, Long> {

    List<Expenditure> findByDateBetween(LocalDate date1, LocalDate date2);

}
