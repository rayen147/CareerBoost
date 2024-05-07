package careerboost.ServiceInterface;


import careerboost.Entity.Commentaire;

import java.util.List;

public interface IcommentaireService {
    List<Commentaire> retrieveAllCommentaires();
    Commentaire addCommentaire(Commentaire commentaire);
    Commentaire updateCommentaire (Commentaire commentaire);
    Commentaire retrieveCommentaire ( Long id);
    void removeCommentaire ( Long id);
   List<Commentaire> retrieveCommentairesForPost(Long postId);
    List<Commentaire> getCommentairesByPostId(Long postId);
}






