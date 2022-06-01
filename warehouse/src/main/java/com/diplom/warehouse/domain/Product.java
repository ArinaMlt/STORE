package com.diplom.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
//@Data
//@ToString(of = {"id", "amount", "price"})
@EqualsAndHashCode(of = {"id"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   /* @ManyToOne
    @JoinColumn(name = "contragent_id")
    private Contragent contragent;*/

    private String productName;
    private String productAmount;
    private String productPrice;
    private Integer productValue;

   /* @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;*/

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @JsonIgnore
    public Invoice getInvoice() {
        return invoice;
    }

    @JsonSetter
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Integer getProductValue() {
        return productValue;
    }

    public void setProductValue(Integer productValue) {
        this.productValue = productValue;
    }
}
