package com.fedorov.tryCW5sem.Entity;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int result;
    private int total;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person person;

    private String subject;
}
