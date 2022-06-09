package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Remainder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemainderRepo extends JpaRepository<Remainder,Long> {

    Page<Remainder> findAll(Pageable pageable);
}
