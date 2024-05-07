package com.example.CareerBoost.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    private String password;

    private Role role;


    /*@OneToMany(mappedBy = "participants")
    private List<Formation> formations; // Formations auxquelles l'utilisateur participe*/
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_formation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "formation_id")
    )
    private Set<Formation> formations = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    //@NotEmpty(message = "La liste des certifs ne peut pas être vide")
    private List<Certificat> certificats;
}