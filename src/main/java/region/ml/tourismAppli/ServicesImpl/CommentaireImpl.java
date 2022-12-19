package region.ml.tourismAppli.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import region.ml.tourismAppli.Repo.CommentaireRepo;
import region.ml.tourismAppli.Repo.RegionRepo;
import region.ml.tourismAppli.Services.CommentaireService;
import region.ml.tourismAppli.modele.Commentaires;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Region;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentaireImpl implements CommentaireService {

    @Autowired
    private CommentaireRepo repo;
    @Autowired
    private RegionRepo regionRepo;

    @Override
    public Commentaires create(Commentaires commentaires) {
        return repo.save(commentaires);
    }


/*    @Override
    public List<Commentaires> readCommentaireByRegion(Long id) {

        Region region = getById(id);

        List<Commentaires> allComments = repo.findAll();

        List<Commentaires> tabComments = new ArrayList<>();

        for (Commentaires comments: allComments){
            try {
                if (comments.getRegion().getId().equals(region.getId())){

                    tabComments.add(comments);
                }

            }catch (Exception e){
                System.err.println(e);
            }
        }

        return tabComments;
    }*/

    @Override
    public List<Commentaires> readCommentaireByRegion(Long id) {

       Region region = regionRepo.findById(id).get();

        List<Commentaires> allCommentaires = repo.findAllByRegion_Id(region.getId());

        return allCommentaires;
    }

    @Override
    public Region getById(Long id) {
        return regionRepo.findById(id).get();
    }
}
