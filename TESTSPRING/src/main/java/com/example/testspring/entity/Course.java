package com.example.testspring.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name="Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long  courseID;
    @Column(name="num")
    Integer num;
    @Enumerated(EnumType.STRING)
    Level level;
    @Column(name="duration")
    Integer duration;
    @Column(name="date")
    LocalDate date;
    @ManyToOne
    Person person;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Person> persons;
    @OneToMany (mappedBy ="course", cascade = CascadeType.ALL)
    List<Exercise> exercises;
}
