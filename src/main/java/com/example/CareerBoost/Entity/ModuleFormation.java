package com.example.CareerBoost.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "module_formation")
public class ModuleFormation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le titre ne peut pas être vide")
    @Size(max = 10, message = "Le titre ne peut pas dépasser {max} caractères")
    @Pattern(regexp = "[a-zA-Z ]*", message = "Le titre ne peut contenir que des lettres")
    private String titre;
    @Pattern(regexp = "[a-zA-Z ]*", message = "La description ne peut contenir que des lettres")
    @NotBlank(message = "La description ne peut pas être vide")
    @Size(max = 255, message = "La description ne peut pas dépasser {max} caractères")
    private String description;
  @JsonIgnore
  //@JsonBackReference
    @ManyToOne
   // @NotNull(message = "La formation ne peut pas être vide")
    @JoinColumn(name = "formation_id")
    private Formation formation;
    @JsonIgnore
   //@JsonManagedReference
    @OneToMany(mappedBy = "moduleFormation")
    private List<Certificat> certificats;

}
