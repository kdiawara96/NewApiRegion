package region.ml.tourismAppli.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import region.ml.tourismAppli.Repo.PaysRepo;
import region.ml.tourismAppli.Repo.RegionRepo;
import region.ml.tourismAppli.Services.RegionService;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Region;

import java.util.List;

@Service
public class RegionImpl implements RegionService {

    @Autowired
    private RegionRepo repo;
    @Autowired
    private PaysRepo paysRepo;

    @Override
    public Region create(Region region) {
            return repo.save(region);
    }


    //Nous recuperons l'id du pays

    @Override
    public Pays getById(Long id) {
        return paysRepo.findById(id).get();
    }

    //On passe le pays en question en paramettre pour recuperer tout les régions liés à ce pays.
    @Override
    public List<Region> readRegionByCountry(Pays pays) {
        return repo.findByPays(pays);
    }


//---------------------------------------------------------------------------




    @Override
    public Region update(Region region) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }



}
