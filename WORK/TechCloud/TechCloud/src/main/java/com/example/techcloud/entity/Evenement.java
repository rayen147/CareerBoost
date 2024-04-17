package com.example.techcloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ToString
@Table(name="evenement")
@CrossOrigin("*")
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="nom")
    String nom;
    @Column(name="nbr_place")
    long nbrPlace;
    @Column(name="date_deb")
    Date date_deb;
    @Column(name="date_fin")
    Date date_fin;
    @Column(name="lieu")
    String lieu;
    //String image;
    @ToStringExclude
    @JsonIgnore
    @OneToMany (mappedBy = "Evenement" , cascade = CascadeType.ALL)

    List<Commentaire> commentaire;

    //@OneToMany(mappedBy = "Evenement", cascade = CascadeType.ALL)
    //@JsonIgnore
    //private List<Reservation> reservations;
}
