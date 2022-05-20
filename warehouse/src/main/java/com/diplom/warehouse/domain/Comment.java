package com.diplom.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

}
