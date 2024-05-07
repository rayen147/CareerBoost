package careerboost.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

//import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Commentaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String contenu;
    @Column(name = "date")
    @NotNull(message = "La date ne peut pas être vide")
    @PastOrPresent(message = "La date  doit être dans le passé ou le présent")
   // @Temporal(TemporalType.DATE)
    private LocalDate datePublication;

    // Relation avec l'auteur (utilisateur)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Relation avec le poste parent
    //@JsonBackReference
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id") // Assurez-vous de spécifier le nom de la colonne approprié si nécessaire
    private Post post;
    // Relation avec les réactions associées
    @JsonIgnore
   // @JsonManagedReference
    @OneToMany(mappedBy = "commentaire", cascade = CascadeType.ALL)
    private List<React> reacts;

    // Getters and setters
}