package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Invoice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice,Long> {
    @EntityGraph(attributePaths = {"products"})
    List<Invoice> findAll();
}
