package region.ml.tourismAppli.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.Repo.PaysRepo;
import region.ml.tourismAppli.Repo.RegionRepo;
import region.ml.tourismAppli.Services.RegionService;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Region;
import region.ml.tourismAppli.others.imageUpload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionImpl implements RegionService {

    @Autowired
    private RegionRepo repo;
    @Autowired
    private PaysRepo paysRepo;


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
    public List<Region> readRegionByCountry(Long id) {

        Pays pays = paysRepo.findById(id).get();

        List<Region> allRegion = repo.findAllByPays_Id(pays.getId());

        return allRegion;
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
