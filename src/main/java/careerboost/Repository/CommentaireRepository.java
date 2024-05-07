package careerboost.Repository;

import careerboost.Entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
    List<Commentaire> findByPostId(Long postId);
}
