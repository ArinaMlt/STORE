package com.diplom.warehouse.controller;

import com.diplom.warehouse.domain.Contragent;
import com.diplom.warehouse.domain.Invoice;
import com.diplom.warehouse.repo.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        String[] subStr;
        String delimeter = "!"; // Разделитель
        subStr = date.split(delimeter); // Разделения строки str с помощью метода split

        String  localDate1 = subStr[0];
        String  localDate2 = subStr[1];
        LocalDate date1 = LocalDate.parse(localDate1);
        LocalDate date2 = LocalDate.parse(localDate2);
        return invoiceRepo.findByDateBetween(date1, date2);
    }

    @PostMapping("byContragent")
    public Page<Invoice> byDate(@RequestBody Contragent contragent,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 1) Pageable pageable){

        return invoiceRepo.findByContragent(contragent, pageable);
    }

    @PostMapping("byId")
    public Optional<Invoice> byDate(@RequestBody Long id){

        return invoiceRepo.findById(id);
    }

    @PostMapping("byPay")
    public List<Invoice> byPay(@RequestBody Boolean pay){

        return invoiceRepo.findByPay(pay);
    }

    @PutMapping("{id}")
    public Invoice update(
            @PathVariable("id") Invoice invoiceFromDb,
            @RequestBody Invoice invoice){
        invoiceFromDb.setContragent(invoice.getContragent());
        invoiceFromDb.setTotalAmount(invoice.getTotalAmount());
        invoiceFromDb.setTotalPrice(invoice.getTotalPrice());
        invoiceFromDb.setTotalValue(invoice.getTotalValue());
        invoiceFromDb.setPay(invoice.getPay());
        invoiceFromDb.setDate(invoice.getDate());
//        BeanUtils.copyProperties(invoice, invoiceFromDb, "id","products");

        return invoiceRepo.save(invoiceFromDb);
    }



    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Invoice invoice){
        invoiceRepo.delete(invoice);
    }
}
