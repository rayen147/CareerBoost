package careerboost.Entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
    @Id
    private Long id;
    private String nom;
    private String role;

    /*@ManyToMany(mappedBy = "participants")
    private Set<React> reacts = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "utilisateur_post",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<Post> posts = new HashSet<>();
*/
}
