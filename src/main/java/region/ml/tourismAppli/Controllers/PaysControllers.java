package region.ml.tourismAppli.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import region.ml.tourismAppli.Repo.PaysRepo;
import region.ml.tourismAppli.Services.PaysServices;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.others.Message;

import java.util.List;

@RestController
@RequestMapping("/pays")
//Swagger
//@Api(value = "pay", description = "Les fonctionnalités liées à un utilisateur simple")
//cette annotation  donne l'accès à cet endpoint à tout utilisateurs
@CrossOrigin
public class PaysControllers {

    @Autowired
    private PaysServices paysService;
    @Autowired
    private PaysRepo paysRepo;
    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> create( Authentication auauthentication, @RequestBody Pays pays){

        try {
            return Message.Response("ok",HttpStatus.OK, paysService.creat(pays));
        }catch (Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Erreur de creation de pays!");
        }

    }

    @GetMapping("/read-all-country")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public List<Pays> lire(Authentication auauthentication){
        return paysRepo.findAll();
    }
}
