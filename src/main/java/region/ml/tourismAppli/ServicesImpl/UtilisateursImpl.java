package region.ml.tourismAppli.ServicesImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.Repo.RolesRepo;
import region.ml.tourismAppli.Repo.UtilisateursRepo;
import region.ml.tourismAppli.Services.UtilisateursService;
import region.ml.tourismAppli.modele.Roles;
import region.ml.tourismAppli.modele.Utilisateurs;
import region.ml.tourismAppli.others.Role;

import java.util.*;

@Service
public class UtilisateursImpl implements UtilisateursService {

    @Autowired
    private UtilisateursRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RolesRepo rRepo;

    //nous allons créer cette methode pour pouvoir encoder les mots de passes


    @Override
    public Utilisateurs create(Utilisateurs utilisateur) {

        //nous allons encripter le code de l'utilisateur
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));


        //Nous donnons le role USER à chaque personne voulant s'inscrire
        String role = "USER";
        Roles roles = rRepo.findByRole(role);
        Set<Roles> rolee = new HashSet<>();
        rolee.add(roles);
        utilisateur.setRole(rolee);

      /* Roles role = new Roles();
        role.setRole("U");
        //Create role
        rRepo.save(role);
        //Collection<String> list = new ArrayList<>();
        //role.setUtilisateur(utilisateur);
        List<Roles> roles = new ArrayList<>();
        roles.add(role);
        utilisateur.setRole(roles);*/


        return repo.save(utilisateur);
    }

    @Override
    public Utilisateurs update(Utilisateurs utilisateur) {

        Utilisateurs user = this.getById(utilisateur.getId());
         System.out.println("Le password de l'utilisateur"+user.getPassword());
         //Nous redonnons les même rôle que le user avait
        utilisateur.setRole(user.getRole());

        //Au cas ou l'utilisateur ne modifie pas son mot de passe nous allons répasser le mot de pass
        //Existant

        if (utilisateur.getPassword() == null || utilisateur.getPassword().equals("")) {

            utilisateur.setPassword(user.getPassword());

            //Si le mot de passe est different de null c-a-d s'il renseigne le mot de passe
        }else if (utilisateur.getPassword() != null) {

            System.out.println("Mot de passe non null");

            utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));

        }

        return repo.save(utilisateur);

    }

    @Override
    public String delete(Long id) {
        repo.deleteById(id);
        return "Supprimer avec succès";
    }

    @Override
    public Utilisateurs login(String email, String password) {

        // TODO Auto-generated method stub

        try {
            Utilisateurs utilisateur = repo.findByEmail(email);

            //passwordEncoder().matches prend le mot de passe cripter dans la base et le mot de passe entrant
            //u.getPassword()

            if (encoder.matches(password, utilisateur.getPassword()) ) {
                //avec utilisateur nous pouvons recuperer tous ses données
                return utilisateur;
            } else
                return null;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }


    @Override
    public Utilisateurs getById(Long id) {
        return repo.findById(id).get();
    }

//Nous avons besoin dans la classe SecurityConfiguartion
    @Override
    public Utilisateurs readByEmail(String email) {
        return repo.findByEmail(email);
    }
}
