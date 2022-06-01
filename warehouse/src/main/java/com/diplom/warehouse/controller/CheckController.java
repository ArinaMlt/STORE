package com.diplom.warehouse.controller;

import com.diplom.warehouse.domain.Expenditure;
import com.diplom.warehouse.repo.CheckRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("check")
public class CheckController {
    private final CheckRepo checkRepo;

    @Autowired
    public CheckController(CheckRepo checkRepo) {
        this.checkRepo = checkRepo;
    }
    @GetMapping
    public List<Expenditure> list(){
        return checkRepo.findAll();
    }

    @GetMapping("{id}")
    public Expenditure getOne(@PathVariable("id") Expenditure check) {
        return check;
    }


    @PostMapping
    public Expenditure create(@RequestBody Expenditure check){
        return checkRepo.save(check);
    }

    @PutMapping("{id}")
    public Expenditure update(
            @PathVariable("id") Expenditure checkFromDb,
            @RequestBody Expenditure check){
        BeanUtils.copyProperties(check, checkFromDb, "id");

        return checkRepo.save(check);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Expenditure check){
        checkRepo.delete(check);
    }

}
