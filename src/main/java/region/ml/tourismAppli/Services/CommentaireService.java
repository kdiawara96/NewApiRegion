package region.ml.tourismAppli.Services;

import region.ml.tourismAppli.modele.Commentaires;
import region.ml.tourismAppli.modele.Region;

import java.util.List;

public interface CommentaireService {

    Commentaires create(Commentaires commentaires);

    List<Commentaires> readCommentaireByRegion(Long id);

    Region getById(Long id);
}
