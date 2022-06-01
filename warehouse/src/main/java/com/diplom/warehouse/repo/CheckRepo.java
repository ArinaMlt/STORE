package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepo extends JpaRepository<Expenditure, Long> {

}
