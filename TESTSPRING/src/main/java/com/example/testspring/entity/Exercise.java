package com.example.testspring.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name="Exercise")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long  exerciceID;
    @Column(name="title")
    String title;

    @Column(name="nbrOfRepetitions")
    Integer nbrOfRepetitions;
    @Column(name="breakTime")
    Integer breakTime;
    @Column(name="duration")
    Integer duration;
    @ManyToOne
    Course course ;
}
