package com.example.CareerBoost.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
=======
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "certificat")
public class Certificat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotNull(message = "L'utilisateur associé au certificat ne peut pas être nul")
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user; // Référence à l'utilisateur ayant suivi le module
   // @NotNull(message = "Le module de formation associé au certificat ne peut pas être nul")
    @ManyToOne
   @JsonIgnore
    //@JsonBackReference
    @JoinColumn(name = "module_formation_id")
    private ModuleFormation moduleFormation; // Référence au module de formation associé
    @NotNull(message = "La date de délivrance ne peut pas être nulle")
    @PastOrPresent(message = "La date de délivrance doit être dans le passé ou la date actuelle")
<<<<<<< HEAD
    //@Temporal(TemporalType.DATE)
=======
    @Temporal(TemporalType.DATE)
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
    private LocalDate dateDelivrance;
    @NotBlank(message = "La description du certificat ne peut pas être vide")
    @Size(max = 100, message = "La description du certificat ne peut pas dépasser {max} caractères")
    @Pattern(regexp = "[a-zA-Z ]*", message = "La description ne peut contenir que des lettres")
    private String description;

    private String statut;//actif, expiré, en attente de validation

}
