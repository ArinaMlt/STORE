package com.diplom.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contragent_id")
    private Contragent contragent;

    private String totalAmount;
    private String totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @OneToMany(mappedBy = "invoice", orphanRemoval = true)
    private List<Product> products;
}
