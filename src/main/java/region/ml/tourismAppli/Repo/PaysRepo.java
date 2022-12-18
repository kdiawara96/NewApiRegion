package region.ml.tourismAppli.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import region.ml.tourismAppli.modele.Pays;

import java.util.Optional;

@Repository
public interface PaysRepo extends JpaRepository<Pays,Long> {

    Pays findByNom(String nom);

}
