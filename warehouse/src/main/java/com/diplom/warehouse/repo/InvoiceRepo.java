package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Contragent;
import com.diplom.warehouse.domain.Invoice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice,Long> {
    @EntityGraph(attributePaths = {"products"})
    List<Invoice> findAll();

//    @Query(value = "SELECT * FROM Invoice u WHERE DATE(date) = ?1", nativeQuery = true)
    List<Invoice> findByDate(LocalDate date);
}
