package region.ml.tourismAppli.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import region.ml.tourismAppli.Services.UtilisateursService;
import region.ml.tourismAppli.modele.Utilisateurs;
import region.ml.tourismAppli.others.MessageError;

@RestController
@RequestMapping("/utilisateurs")
//Swagger
//@Api(value = "utilisateur", description = "Les fonctionnalités liées à un utilisateur simple")
//cette annotation  donne l'accès à cet endpoint à tout utilisateurs
@CrossOrigin
public class UtilisateursController {

    @Autowired
    private UtilisateursService userService;

    // Pour le login d'un utilisateur
    //Sawagger
   // @ApiOperation(value = "Pour le login d'un utilisateur.")
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody Utilisateurs utilisateur) {

        System.out.println(utilisateur.getEmail());
        System.out.println(utilisateur.getPassword());

       Utilisateurs user = userService.login(utilisateur.getEmail(),utilisateur.getPassword());

       if (user!=null){
           return MessageError.ErrorResponse("ok", HttpStatus.OK, user);
       }
       else {
           return MessageError.ErrorResponse("none", HttpStatus.OK, "E-mail ou mot de passe incorrecte");
       }


    }
    }
