package careerboost.ServiceInterface;

import careerboost.Entity.React;

import java.util.List;

public interface IreactService {
    List<React> retrieveAllReacts();
    React addReact(React react);
    React updateReact (React react);
    React retrieveReact ( Long id);
    void removeReact ( Long id);
    List<React> retrieveReactsForPost(Long postId);
}






