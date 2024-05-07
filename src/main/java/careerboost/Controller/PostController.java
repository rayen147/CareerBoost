package careerboost.Controller;

import careerboost.Entity.Post;
import careerboost.Entity.React;
import careerboost.ServiceInterface.IpostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Tag(name = "gestion Forum")
@RestController

@RequestMapping("/post")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
    private final IpostService ipostService;


    @GetMapping("/retrieveAllPost")
    public List<Post> retrieveAllPosts() {
        return ipostService.retrieveAllPosts();
    }

    //postman
    @PostMapping("/addPost")
    public ResponseEntity<Map<String, Object>> addPost(@Valid @RequestBody Post post, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            Post addedPost = ipostService.addPost(post);
            response.put("success", true);
            response.put("message", "Post ajouté avec succès");
            response.put("post", addedPost);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping("/{postId}/reactions")
    public ResponseEntity<React> addReactionToPost(@PathVariable Long postId, @Valid @RequestBody React reaction) {
        // Récupérer la publication par son ID
        Post post = ipostService.retrievePost(postId);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        // Assurez-vous que la réaction est liée au post spécifié
        reaction.setPost(post);

        // Ajouter la réaction à la publication
        React addedReaction = ipostService.addReactionToPost(postId, reaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedReaction);
    }

    /*@PutMapping("/updatePost")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updatePost(@Valid @RequestBody Post post, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            Post updatedPost = ipostService.updatePost(post);
            response.put("success", true);
            response.put("message", "Post mis à jour avec succès");
            response.put("post", updatedPost);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }*/
    @PutMapping("/updatePost/{id}")
    public ResponseEntity<Map<String, Object>> updatePost(@PathVariable Long id, @Valid @RequestBody Post post, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            Post existingPost = ipostService.retrievePost(id);
            if (existingPost == null) {
                response.put("success", false);
                response.put("message", "Post not found");

                return ResponseEntity.notFound().build();
            }


            // Mettre à jour les champs du certificat existant avec les valeurs fournies
            existingPost.setDate(post.getDate());
            existingPost.setContenu(post.getContenu());
            existingPost.setTitre(post.getTitre());


            // Mettre à jour le certificat dans la base de données
            Post updatedPost = ipostService.updatePost(existingPost);

            // Répondre avec les détails du certificat mis à jour
            response.put("success", true);
            response.put("message", "Post mis à jour avec succès");
            response.put("Post", updatedPost);
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/removePost/{id}")
    public ResponseEntity<String> removePost(@PathVariable("id") Long id) {
        try {
            ipostService.removePost(id);
            return ResponseEntity.ok("Post supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression du post");
        }
    }

    @GetMapping({"/retrievePost/{id}"})
    @ResponseBody
    public Post retrievePost(@PathVariable Long id) {
        return this.ipostService.retrievePost(id);
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable Long postId) {
        try {
            // Appel de la méthode du service pour aimer le post
            ipostService.likePost(postId);
            // Retourne une réponse 200 OK si le like a été ajouté avec succès
            return ResponseEntity.ok(new String[]{"Post liked successfully."});
        } catch (EntityNotFoundException e) {
            // Retourne une réponse avec le statut NOT_FOUND si le post avec l'ID spécifié n'a pas été trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /* @GetMapping({"/deletePost/{id}"})
     public String getDeletePost(@PathVariable Long id, Model model) {
         Post post = this.ipostService.retrievePost(id);
         if (post == null) {
             return "redirect:/post/retrieveallPost";
         } else {
             model.addAttribute("post", post);
             return "post/deletePost";
         }
     }

     @PostMapping({"/deletePost/{id}"})
     public String deletePost(@RequestParam("id") Long id) {
         this.ipostService.removePost(id);
         return "post/Post deleted successfully";
     }*/
    @GetMapping("/searchPostsByTitle/{titre}")
    public ResponseEntity<?> searchPostsByTitle(@PathVariable String titre) {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(ipostService.searchPostsByTitle(titre));
        } catch (EntityNotFoundException e) {
            // Retourne une réponse avec le statut NOT_FOUND si le post avec l'ID spécifié n'a pas été trouvé
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/likesCountPerPost")
    public ResponseEntity<Map<String, Integer>> getLikesCountPerPost() {
        Map<String, Integer> likesCountMap = ipostService.getLikesCountPerPost();
        return ResponseEntity.ok(likesCountMap);
    }
}
