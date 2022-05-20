package com.diplom.warehouse.controller;

import com.diplom.warehouse.Service.CommentService;
import com.diplom.warehouse.domain.Comment;
import com.diplom.warehouse.domain.Message;
import com.diplom.warehouse.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentRepo commentRepo;

    @Autowired
    public CommentController(CommentService commentService, CommentRepo commentRepo) {
        this.commentService = commentService;
        this.commentRepo = commentRepo;
    }
    @GetMapping
    public List<Comment> list(){
        return commentRepo.findAll();
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment){
        return commentService.create(comment);
    }
}
