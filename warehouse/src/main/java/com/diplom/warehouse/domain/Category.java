package com.diplom.warehouse.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameCategory;
}
