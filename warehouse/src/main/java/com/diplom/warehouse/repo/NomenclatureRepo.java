package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NomenclatureRepo extends JpaRepository<Nomenclature,Long> {
}
