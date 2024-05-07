package careerboost.Repository;

import careerboost.Entity.React;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactRepository extends JpaRepository<React,Long> {
    List<React> findByPostId(Long postId);
}
