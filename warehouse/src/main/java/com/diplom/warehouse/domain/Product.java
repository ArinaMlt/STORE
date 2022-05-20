package com.diplom.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table
@ToString(of = {"id", "name", "amount", "price"})
@EqualsAndHashCode(of = {"id"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   /* public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }*/
    private String name;
    private String amount;
    private String price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime localDateTime;


}
