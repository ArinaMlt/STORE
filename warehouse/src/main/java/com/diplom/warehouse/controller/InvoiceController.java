package com.diplom.warehouse.controller;

import com.diplom.warehouse.domain.Contragent;
import com.diplom.warehouse.domain.Invoice;
import com.diplom.warehouse.repo.InvoiceRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("byDate")
    public List<Invoice> byDate(@RequestBody String date){
        LocalDate localDate = LocalDate.parse(date);
        return invoiceRepo.findByDate(localDate);
    }

    @PutMapping("{id}")
    public Invoice update(
            @PathVariable("id") Invoice invoiceFromDb,
            @RequestBody Invoice invoice){
        invoiceFromDb.setContragent(invoice.getContragent());
        invoiceFromDb.setTotalAmount(invoice.getTotalAmount());
        invoiceFromDb.setTotalPrice(invoice.getTotalPrice());
        invoiceFromDb.setDate(invoice.getDate());
//        BeanUtils.copyProperties(invoice, invoiceFromDb, "id","products");

        return invoiceRepo.save(invoiceFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Invoice invoice){
        invoiceRepo.delete(invoice);
    }
}
