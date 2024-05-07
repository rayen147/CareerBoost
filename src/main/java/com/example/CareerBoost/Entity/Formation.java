package com.example.CareerBoost.Entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
<<<<<<< HEAD

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "formation")
public class Formation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le titre ne peut pas être vide")
    @Size(max = 10, message = "Le titre ne peut pas dépasser {max} caractères")
    @Pattern(regexp = "[a-zA-Z ]*", message = "Le titre ne peut contenir que des lettres")
    private String titre;
    @NotBlank(message = "La description ne peut pas être vide")
    @Size(max = 255, message = "La description ne peut pas dépasser {max} caractères")
    @Pattern(regexp = "[a-zA-Z ]*", message = "La description ne peut contenir que des lettres")
    private String description;
    @Column(name = "date_debut")
    @NotNull(message = "La date de début ne peut pas être vide")
    @PastOrPresent(message = "La date de début doit être dans le passé ou le présent")
    private LocalDate dateDebut; // Date de début de la formation
    @NotNull(message = "La date de fin ne peut pas être vide")
    @FutureOrPresent(message = "La date de fin doit être dans le passé ou le présent")
    @Column(name = "date_fin")
    private LocalDate dateFin;
    // Date de fin de la formation
<<<<<<< HEAD
    @Column(name = "limite_participants")
    private Integer limiteParticipants; // Utilisez Integer au lieu de int
    // Nombre maximum de participants autorisés pour la formation
   @JsonIgnore
    //@JsonManagedReference
=======
    //@JsonIgnore
    @JsonManagedReference
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
    @OneToMany(mappedBy = "formation")
    //@NotEmpty(message = "La liste des modules ne peut pas être vide")
    private List<ModuleFormation> modules;
    @JsonIgnore
   @ManyToMany(mappedBy = "formations")
  // @NotEmpty(message = "La liste des participants ne peut pas être vide")
    private Set<User> participants = new HashSet<>(); // Utilisateurs participant à la formation (HashSet garantit que chaque utilisateur est unique pas de repetition des utilisateurs)
    @JsonIgnore
    @ManyToMany(mappedBy = "formations")
    //@NotEmpty(message = "La liste des formateurs ne peut pas être vide")
    private Set<User> formateurs = new HashSet<>(); // Utilisateurs formateurs de formation
<<<<<<< HEAD
    public void addParticipant(User participant) {
        if (this.participants.size() < this.limiteParticipants) {
            this.participants.add(participant);
        } else {
            throw new IllegalStateException("La formation est pleine. Impossible d'ajouter plus de participants.");
        }
    }
=======

>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
}
