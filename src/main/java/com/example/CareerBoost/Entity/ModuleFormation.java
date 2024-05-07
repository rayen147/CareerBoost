package com.example.CareerBoost.Entity;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
=======
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
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
<<<<<<< HEAD
  @JsonIgnore
  //@JsonBackReference
=======
  //  @JsonIgnore
  @JsonBackReference
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
    @ManyToOne
   // @NotNull(message = "La formation ne peut pas être vide")
    @JoinColumn(name = "formation_id")
    private Formation formation;
<<<<<<< HEAD
    @JsonIgnore
   //@JsonManagedReference
=======
   // @JsonIgnore
   @JsonManagedReference
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
    @OneToMany(mappedBy = "moduleFormation")
    private List<Certificat> certificats;

}
