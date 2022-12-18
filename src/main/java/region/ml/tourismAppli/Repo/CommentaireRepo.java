package region.ml.tourismAppli.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import region.ml.tourismAppli.modele.Commentaires;

public interface CommentaireRepo extends JpaRepository<Commentaires, Long> {
}
