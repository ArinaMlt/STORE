package com.diplom.warehouse.controller;

import com.diplom.warehouse.domain.Invoice;
import com.diplom.warehouse.repo.InvoiceRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("invoice")
public class InvoiceController {
    private final InvoiceRepo invoiceRepo;

    @Autowired
    public InvoiceController(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }


    @GetMapping
    public List<Invoice> list(){
        return invoiceRepo.findAll();
    }

    @GetMapping("{id}")
    public Invoice getOne(@PathVariable("id") Invoice invoice) {
        return invoice;
    }

    @PostMapping
    public Invoice create(@RequestBody Invoice invoice){
        return invoiceRepo.save(invoice);
    }

    @PutMapping("{id}")
    public Invoice update(
            @PathVariable("id") Invoice invoiceFromDb,
            @RequestBody Invoice invoice){
        BeanUtils.copyProperties(invoice, invoiceFromDb, "id");

        return invoiceRepo.save(invoice);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Invoice invoice){
        invoiceRepo.delete(invoice);
    }
}
