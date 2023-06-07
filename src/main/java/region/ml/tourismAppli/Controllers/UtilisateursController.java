package region.ml.tourismAppli.Controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.Services.ImagesService;
import region.ml.tourismAppli.Services.UtilisateursService;
import region.ml.tourismAppli.modele.Images;
import region.ml.tourismAppli.modele.Utilisateurs;
import region.ml.tourismAppli.others.Message;

import java.io.IOException;

@RestController
@RequestMapping("/utilisateurs")
//Swagger
//@Api(value = "utilisateur", description = "Les fonctionnalités liées à un utilisateur simple")
//cette annotation  donne l'accès à cet endpoint à tout utilisateurs
@CrossOrigin
public class UtilisateursController {

    @Autowired
    private UtilisateursService userService;
    @Autowired
    private ImagesService imageService;






    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public ResponseEntity<Object> createUser(Authentication auauthentication, @RequestParam("image") MultipartFile file, @RequestParam("data") String users)  {



        try {

            Images img =  imageService.saveImage(file);

            Utilisateurs utilisateurs = new Utilisateurs();

            utilisateurs = new JsonMapper().readValue(users, Utilisateurs.class);
            utilisateurs.setImage(img);

            Utilisateurs user = userService.create(utilisateurs);

            return Message.Response("ok", HttpStatus.OK, user);
        }catch (Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Erreur d'insersion!");
        }
    }

    
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public ResponseEntity<Object> update(Authentication auauthentication, @RequestBody Utilisateurs utilisateurs){
        try{
            return   Message.Response("ok", HttpStatus.OK , userService.update(utilisateurs));
        }catch (Exception e){
            return   Message.Response("none", HttpStatus.BAD_REQUEST ,utilisateurs+" non modifié...");
        }
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> DeleteUser(Authentication auauthentication, @PathVariable Long id){
        try{
            return   Message.Response("ok", HttpStatus.OK , userService.delete(id));
        }catch (Exception e){
            return   Message.Response("none", HttpStatus.BAD_REQUEST ,"L'utilisateur avec l'id : "+ id +" non trouver...");
        }
    }


    @GetMapping("Read-all-user")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> readeAllUser(Authentication auauthentication) {
        try{
            return Message.Response("ok", HttpStatus.OK,userService.readeAllUser());
        }catch (Exception e){
            return Message.Response("none", HttpStatus.OK,"Erreur d'affichage!");
        }
    }







    // Pour le login d'un utilisateur
    //Sawagger
   // @ApiOperation(value = "Pour le login d'un utilisateur.")


  /*  @PostMapping("/login")
    @ResponseBody
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public ResponseEntity<Object> login(Authentication auauthentication , @RequestBody Utilisateurs utilisateur) {

        System.out.println(utilisateur.getEmail());
        System.out.println(utilisateur.getPassword());

       Utilisateurs user = userService.login(utilisateur.getEmail(),utilisateur.getPassword());

       if (user!=null){
           return Message.Response("ok", HttpStatus.OK, user);
       }
       else {
           return Message.Response("none", HttpStatus.BAD_REQUEST, "E-mail ou mot de passe incorrecte");
       }


    }
*/





}

