package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Message;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    @Override
    @EntityGraph(attributePaths = {"comments"})
    List<Message> findAll();
}
