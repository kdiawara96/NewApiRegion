package region.ml.tourismAppli.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import region.ml.tourismAppli.Services.CommentaireService;
import region.ml.tourismAppli.modele.Commentaires;
import region.ml.tourismAppli.others.Message;

@RestController
@RequestMapping("/comments")
public class CommentairesController {

    @Autowired
    private CommentaireService commentaireService;

    @PostMapping("/create")
    @ResponseBody
   @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public ResponseEntity<Object> createComments( Authentication auauthentication, @RequestBody Commentaires comments){

        try {
            return Message.Response("ok", HttpStatus.OK, commentaireService.create(comments));
        }catch (Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Commentaires non envoyé!");
        }
    }

    @GetMapping("/readCommentsByRegion/{id}")
    @ResponseBody
   @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public ResponseEntity<Object> readCommentsByRegion( Authentication auauthentication, @PathVariable Long id){
        try {
            return Message.Response("ok", HttpStatus.OK, commentaireService.readCommentaireByRegion(id));
        }catch (Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Erreur d'affichage!");
        }

    }




}
