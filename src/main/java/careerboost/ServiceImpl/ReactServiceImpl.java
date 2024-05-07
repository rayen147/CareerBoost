package careerboost.ServiceImpl;


import careerboost.Entity.React;
import careerboost.Repository.ReactRepository;
import careerboost.ServiceInterface.IreactService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ReactServiceImpl implements IreactService {
    ReactRepository reactRepository;


    @Override
    public List<React> retrieveAllReacts() {
        return reactRepository.findAll();
    }
    public List<React> retrieveReactsForPost(Long postId) {
        return reactRepository.findByPostId(postId);
    }
    @Override
    public React retrieveReact(Long id) {
        return reactRepository.findById(id).orElse(null);
    }

    @Override
    public void removeReact(Long id) {
       reactRepository.deleteById(id);
    }

    @Override
    public React updateReact(@NotNull @Valid React react) {
        return reactRepository.save(react);
    }

    @Override
    public React addReact(@NotNull @Valid React react) {
        return reactRepository.save(react) ;    }
}


