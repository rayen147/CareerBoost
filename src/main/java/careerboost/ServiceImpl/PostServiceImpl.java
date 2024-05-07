package careerboost.ServiceImpl;


import careerboost.Entity.Post;
import careerboost.Entity.React;
import careerboost.Repository.PostRepository;
import careerboost.Repository.ReactRepository;
import careerboost.ServiceInterface.IpostService;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements IpostService {
    PostRepository postRepository;
    ReactRepository reactRepository;

    @Override
    public List<Post> retrieveAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post addPost(@NotNull @Valid Post post) {
        return  postRepository.save(post);
    }

    @Override
    public Post updatePost(@NotNull @Valid Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post retrievePost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public void removePost(Long id) {
     postRepository.deleteById(id);
    }
    @Override
    public void likePost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // Vérifier si likeCount est null, et le remplacer par 0 si c'est le cas
            if (post.getLikeCount() == null) {
                post.setLikeCount(0);
            }
            // Incrémenter le compteur de likes
            post.setLikeCount(post.getLikeCount() + 1);
            postRepository.save(post);
        } else {
            // Gérer le cas où le post n'est pas trouvé
            throw new EntityNotFoundException("Post not found with id: " + postId);
        }
    }

    @Override
    public React addReactionToPost(Long postId, React reaction) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new IllegalArgumentException("Post not found with ID: " + postId);
        }

        reaction.setPost(post);
        return reactRepository.save(reaction);
    }
   /* public void likePost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setLikeCount(post.getLikeCount() + 1);
            postRepository.save(post);
        } else {
            throw new EntityNotFoundException("Post not found with id: " + postId);
        }
    }*/

   @Override
   public List<Post> searchPostsByTitle(String titre) {
       return postRepository.findByTitreContainingIgnoreCase(titre);
   }
    @Override
    public Map<String, Integer> getLikesCountPerPost() {
        Map<String, Integer> likesCountMap = new HashMap<>();
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            likesCountMap.put(post.getTitre(), post.getLikeCount());
        }
        return likesCountMap;
    }
}


