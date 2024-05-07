package careerboost.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.*;

//import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.stream.Stream;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class React implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    @NotNull(message = "La date de react ne peut pas être vide")
    @PastOrPresent(message = "La date de react doit être dans le passé ou le présent")
    private LocalDate date;
    @JsonIgnore
    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "post_id") // Nom de la colonne dans la table "React" qui fait référence à la clé primaire de "Post"
    private Post post;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    @JsonIgnore
    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "commentaire_id")
    private Commentaire commentaire;

    @Enumerated(EnumType.STRING)
    private TypeReact typeReact;
    public static TypeReact getTypeReactFromString(String typeReact) {
        return Stream.of(TypeReact.values())
                .filter(value -> value.name().equalsIgnoreCase(typeReact))
                .findFirst()
                .orElse(null);}
    }
