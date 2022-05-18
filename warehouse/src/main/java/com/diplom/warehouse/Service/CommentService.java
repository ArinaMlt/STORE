package com.diplom.warehouse.Service;

import com.diplom.warehouse.domain.Comment;
import com.diplom.warehouse.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }
    public Comment create(Comment comment){
        commentRepo.save(comment);
        return comment;
    }
}
