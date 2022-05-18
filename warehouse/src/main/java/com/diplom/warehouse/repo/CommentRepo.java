package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
