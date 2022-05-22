package com.diplom.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table
@Data
//@ToString(of = {"id", "amount", "price"})
@EqualsAndHashCode(of = {"id"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contragent_id")
    private Contragent contragent;

//    private String name;
    private String amount;
    private String price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
