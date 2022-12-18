package region.ml.tourismAppli.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import region.ml.tourismAppli.modele.Region;
import region.ml.tourismAppli.modele.Roles;
import region.ml.tourismAppli.modele.Utilisateurs;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateursRepo extends JpaRepository<Utilisateurs, Long> {


   // Utilisateurs findByLogin(String login);

    Utilisateurs findByEmailAndPassword(String email, String password);

    Utilisateurs findByEmail(String email);


    Optional<Utilisateurs> findByImagename(String fileName);
}
