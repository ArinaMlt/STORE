package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Contragent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContragentRepo extends JpaRepository<Contragent,Long> {
   @EntityGraph(attributePaths = {"nomenclatures"})
    List<Contragent> findAll();

   List<Contragent> findByName(String name);
}
