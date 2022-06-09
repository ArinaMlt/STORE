package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Contragent;
import com.diplom.warehouse.domain.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
//import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepo extends JpaRepository<Invoice,Long> {
    @EntityGraph(attributePaths = {"products"})
    List<Invoice> findAll();

//    @Query(value = "SELECT * FROM Invoice u WHERE DATE(date) = ?1", nativeQuery = true)
//    List<Invoice> findByDate(LocalDate date);

    List<Invoice> findByDateBetween(LocalDate date1, LocalDate date2);

    Page<Invoice> findByContragent(Contragent contragent, Pageable pageable);
//    Page<Invoice> findByContragent(Contragent contragent, Pageable pageable);

    List<Invoice> findByPay(Boolean pay);

    Optional<Invoice> findById(Long id);
}
