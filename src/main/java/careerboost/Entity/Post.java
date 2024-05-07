package careerboost.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


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
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le titre ne peut pas être vide")
    @Size(max = 10, message = "Le titre ne peut pas dépasser {max} caractères")
    private String titre;
    @NotBlank(message = "Le contenu ne peut pas être vide")
    //@Size(min = 10, max = 1000, message = "Le contenu doit avoir entre 10 et 1000 caractères")

    @Column(columnDefinition = "TEXT")
    private String contenu;
    @Column(name = "date")
    @NotNull(message = "La date  ne peut pas être vide")
    @PastOrPresent(message = "La date  doit être dans le passé ou le présent")
    private LocalDate date;
    @Column(name = "like_count")
    private Integer likeCount;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    @JsonIgnore
    //@JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;
    @JsonIgnore
    //@JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<React> reacts;
}
