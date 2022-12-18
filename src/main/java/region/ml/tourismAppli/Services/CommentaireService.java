package region.ml.tourismAppli.Services;

import org.springframework.beans.factory.annotation.Autowired;
import region.ml.tourismAppli.Repo.CommentaireRepo;
import region.ml.tourismAppli.modele.Commentaires;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Region;

import java.util.List;

public interface CommentaireService {

    Commentaires create(Commentaires commentaires);

    List<Commentaires> readCommentaireByRegion(Long id);

    Region getById(Long id);
}
