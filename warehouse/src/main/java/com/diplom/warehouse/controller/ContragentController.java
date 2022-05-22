package com.diplom.warehouse.controller;

import com.diplom.warehouse.domain.Contragent;
import com.diplom.warehouse.domain.Message;
import com.diplom.warehouse.repo.ContragentRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contragent")
public class ContragentController {
    private final ContragentRepo contragentRepo;

    @Autowired
    public ContragentController(ContragentRepo contragentRepo) {
        this.contragentRepo = contragentRepo;
    }

    @GetMapping
    public List<Contragent> list(){
        return contragentRepo.findAll();
    }

    @GetMapping("{id}")
    public Contragent getOne(@PathVariable("id") Contragent contragent) {
        return contragent;
    }

    @PostMapping
    public Contragent create(@RequestBody Contragent contragent){
        return contragentRepo.save(contragent);
    }

    @PostMapping("filter")
    public List<Contragent> filter(@RequestBody String name){
        return contragentRepo.findByName(name);
    }

    @PutMapping("{id}")
    public Contragent update(
            @PathVariable("id") Contragent contragentFromDb,
            @RequestBody Contragent contragent){
        BeanUtils.copyProperties(contragent, contragentFromDb, "id");

        return contragentRepo.save(contragent);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Contragent contragent){
        contragentRepo.delete(contragent);

    }
}
