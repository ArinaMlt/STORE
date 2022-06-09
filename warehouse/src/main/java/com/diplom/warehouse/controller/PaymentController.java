package com.diplom.warehouse.controller;

import com.diplom.warehouse.domain.Payment;
import com.diplom.warehouse.repo.PaymentRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {
    private final PaymentRepo paymentRepo;

    @Autowired
    public PaymentController(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @GetMapping
    public List<Payment> list(){
        return paymentRepo.findAll();
    }

    @GetMapping("{id}")
    public Payment getOne(@PathVariable("id") Payment payment) {
        return payment;
    }

    @PostMapping
    public Payment create(@RequestBody Payment payment){
        return paymentRepo.save(payment);
    }
    @PutMapping("{id}")
    public Payment update(
            @PathVariable("id") Payment paymentFromDb,
            @RequestBody Payment payment){
        BeanUtils.copyProperties(payment, paymentFromDb, "id");

        return paymentRepo.save(payment);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Payment payment){
        paymentRepo.delete(payment);
    }
}
