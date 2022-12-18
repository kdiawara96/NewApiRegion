package region.ml.tourismAppli.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> createComments(Commentaires comments){

        try {
            return Message.Response("ok", HttpStatus.OK, commentaireService.create(comments));
        }catch (Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Commentaires non envoyé!");
        }
    }

    @PostMapping("/readCommentsByRegion/{id}")
    @ResponseBody
    public ResponseEntity<Object> readCommentsByRegion(@PathVariable Long id){
        try {
            return Message.Response("ok", HttpStatus.OK, commentaireService.readCommentaireByRegion(id));
        }catch (Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Erreur d'affichage!");
        }

    }




}
