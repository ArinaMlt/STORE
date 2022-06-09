package com.diplom.warehouse.controller;


import com.diplom.warehouse.domain.Remainder;
import com.diplom.warehouse.repo.RemainderRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("remainder")
public class RemainderController {
    private final RemainderRepo remainderRepo;

    @Autowired
    public RemainderController(RemainderRepo remainderRepo) {
        this.remainderRepo = remainderRepo;
    }

    @GetMapping
    public Page<Remainder> list (@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 1) Pageable pageable){
        return remainderRepo.findAll(pageable);
    }

    @GetMapping("{id}")
    public Remainder getOne(@PathVariable("id") Remainder remainder) {
        return remainder;
    }

    @PostMapping
    public Remainder create(@RequestBody Remainder remainder){
//        return productService.create(product);
        return remainderRepo.save(remainder);
    }

  /*  @PostMapping("find")
    public Page<Remainder> find(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 1) Pageable pageable
    ){

        return remainderRepo.findAll(pageable);
    }*/

    @PutMapping("{id}")
    public Remainder update(
            @PathVariable("id") Remainder remainderFromDb,
            @RequestBody Remainder remainder){
        BeanUtils.copyProperties(remainder, remainderFromDb, "id");

        return remainderRepo.save(remainder);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Remainder remainder){
        remainderRepo.delete(remainder);
    }
}
