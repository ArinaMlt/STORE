package com.diplom.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
//@Data
@EqualsAndHashCode(of = {"id"})
public class Nomenclature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String category;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "contragent_id")
    private Contragent contragent;

    public Long getId() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @JsonIgnore
    public Contragent getContragent() {
        return contragent;
    }

    @JsonSetter
    public void setContragent(Contragent contragent) {
        this.contragent = contragent;
    }
}
