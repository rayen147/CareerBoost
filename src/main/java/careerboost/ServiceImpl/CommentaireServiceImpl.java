package careerboost.ServiceImpl;

import careerboost.Entity.Commentaire;
import careerboost.Repository.CommentaireRepository;
import careerboost.ServiceInterface.IcommentaireService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor

public class CommentaireServiceImpl implements IcommentaireService {

    //@Autowired
    private CommentaireRepository commentaireRepository;

    @Override
    public List<Commentaire> retrieveAllCommentaires() {
        return commentaireRepository.findAll();
    }
    @Override
    public List<Commentaire> retrieveCommentairesForPost(Long postId) {
        return commentaireRepository.findByPostId(postId);
    }
    @Override
    public List<Commentaire> getCommentairesByPostId(Long postId) {
        return commentaireRepository.findByPostId(postId);
    }

    @Override
    public Commentaire addCommentaire(@NotNull @Valid Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public Commentaire updateCommentaire(@NotNull @Valid Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public Commentaire retrieveCommentaire(Long id) {
        return commentaireRepository.findById(id).orElse(null);
    }

    @Override
    public void removeCommentaire(Long id) {
        commentaireRepository.deleteById(id);
    }
}


