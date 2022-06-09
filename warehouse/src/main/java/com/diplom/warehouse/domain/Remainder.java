package com.diplom.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
public class Remainder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Double sumInvoice;
    private Double sumCheck;
    private String period;
    private Double remind;
}
