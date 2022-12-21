package region.ml.tourismAppli.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import region.ml.tourismAppli.Repo.UtilisateursRepo;
import region.ml.tourismAppli.modele.Utilisateurs;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;



@Service
@AllArgsConstructor
//Cette annotation nous permettra d'utiliser les logs pour verifier tous les mouvements de user
@Slf4j
public class PersonnaliserUtilisateurDetailService implements UserDetailsService {

    @Autowired
    private UtilisateursRepo repo;



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email.trim().isEmpty()){
            throw new UsernameNotFoundException("Le nom "+email+" n'existe pas!");
        }
        //Nous allons recuperer le user par son nom
        Utilisateurs utilisateurs = repo.findByEmail(email);
        if (utilisateurs == null){
            log.error("L'utilisateur"+ email +"n'a pas été trouver!");
            throw new UsernameNotFoundException("L'utilisateur"+ email +"n'a pas été trouver!");
        }else{
            log.error("L'utilisateur trouver!" ,email);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateurs.getRole().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
        return new User(utilisateurs.getEmail(),utilisateurs.getPassword(),authorities);
    }
}
