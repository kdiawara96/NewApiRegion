package region.ml.tourismAppli.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import region.ml.tourismAppli.modele.Commentaires;
import region.ml.tourismAppli.modele.Region;

import java.util.List;

public interface CommentaireRepo extends JpaRepository<Commentaires, Long> {

    List<Commentaires> findAllByRegion_Id(Long id);
}
