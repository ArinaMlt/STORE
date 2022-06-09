package com.diplom.warehouse.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
//@ToString(of = {"id","name", "inn", "address"})
@Data
@EqualsAndHashCode(of = {"id"})
public class Contragent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String inn;
    private String address;

    @OneToMany(mappedBy = "contragent", orphanRemoval = true)
    private List<Nomenclature> nomenclatures;

}

