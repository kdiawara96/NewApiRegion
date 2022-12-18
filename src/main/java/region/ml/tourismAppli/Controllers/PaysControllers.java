package region.ml.tourismAppli.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import region.ml.tourismAppli.Services.PaysServices;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.others.Message;

@RestController
@RequestMapping("/pays")
//Swagger
//@Api(value = "pay", description = "Les fonctionnalités liées à un utilisateur simple")
//cette annotation  donne l'accès à cet endpoint à tout utilisateurs
@CrossOrigin
public class PaysControllers {

    @Autowired
    private PaysServices paysService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody Pays pays){

        try {
            return Message.Response("ok",HttpStatus.OK, paysService.creat(pays));
        }catch (Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Erreur de creation de pays!");
        }

    }
}
