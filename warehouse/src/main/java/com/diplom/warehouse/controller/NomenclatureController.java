package com.diplom.warehouse.controller;

import com.diplom.warehouse.domain.Nomenclature;
import com.diplom.warehouse.repo.NomenclatureRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("nomenclature")
public class NomenclatureController {
    private final NomenclatureRepo nomenclatureRepo;

    @Autowired
    public NomenclatureController(NomenclatureRepo nomenclatureRepo) {
        this.nomenclatureRepo = nomenclatureRepo;
    }

    @GetMapping
    public List<Nomenclature> list(){
        return nomenclatureRepo.findAll();
    }

    @GetMapping("{id}")
    public Nomenclature getOne(@PathVariable("id") Nomenclature nomenclature) {
        return nomenclature;
    }

    @PostMapping
    public Nomenclature create(@RequestBody Nomenclature nomenclature){
//        return productService.create(product);
        return nomenclatureRepo.save(nomenclature);
    }
    @PutMapping("{id}")
    public Nomenclature update(
            @PathVariable("id") Nomenclature nomenclatureFromDb,
            @RequestBody Nomenclature nomenclature){
        BeanUtils.copyProperties(nomenclature, nomenclatureFromDb, "id");

        return nomenclatureRepo.save(nomenclature);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Nomenclature nomenclature){
        nomenclatureRepo.delete(nomenclature);
    }
}
