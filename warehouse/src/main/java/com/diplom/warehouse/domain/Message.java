package com.diplom.warehouse.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    @OneToMany(mappedBy = "message", orphanRemoval = true)
    private List<Comment> comments;


}
