package com.example.testspring.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Person")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long  personID;
    @Column(name="name")
    String name;
    @Enumerated(EnumType.STRING)
     Role role;
    @Column(name="nbrMonthsTraining")
    Integer nbrMonthsTraining;

    @Column(name="dateOfBirth")
    LocalDate dateOfBirth;
    @OneToMany (mappedBy = "person" , cascade = CascadeType.ALL)

    List<Course> courses;
    @ManyToMany(mappedBy="persons" ,cascade = CascadeType.ALL)
    List<Course> course;


}
