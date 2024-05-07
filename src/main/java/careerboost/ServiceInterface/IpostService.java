package careerboost.ServiceInterface;

import careerboost.Entity.Post;
import careerboost.Entity.React;
//import careerboost.entity.Commentaire;

import java.util.List;
import java.util.Map;


public interface IpostService {
    List<Post> retrieveAllPosts();
    Post addPost(Post post);
    Post updatePost (Post post);
    Post retrievePost ( Long id);
    void removePost ( Long id);
  void likePost(Long postId) ;
    public React addReactionToPost(Long postId, React reaction);
    public List<Post> searchPostsByTitle(String titre);
    public Map<String, Integer> getLikesCountPerPost();

}

















