package region.ml.tourismAppli.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import region.ml.tourismAppli.Repo.PaysRepo;
import region.ml.tourismAppli.Repo.RegionRepo;
import region.ml.tourismAppli.Services.RegionService;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Region;

import java.util.ArrayList;
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
    public List<Region> readRegionByCountry(Long id) {

          Pays pays = getById(id);

       List<Region> allRegion = repo.findAll();

       //nous créons un tableau dynamique de Region qui va englober tout les regions du pays donner
       List<Region>  CartonRegions = new ArrayList<>();

       //Nous allons itterer sur l'ensemble des region (allRegion)
       for (Region region : allRegion ){

           try {
               //Nous allons prendre chaque region et verifier si le id du pays = au id du pays dans la region
               //et si ces true nous allons l'ajouter dans le tableau et au final renvoyer le tableau
               if (region.getPays().getId().equals(pays)){

                   System.err.println("------------------------------------------------------------------------");
                   System.err.println(region);
                   CartonRegions.add(region);
               }

           }catch (Exception e){
              System.out.println(e);
           }

        }

       //la liste des régions par rapport a un pays donner
        return CartonRegions;
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
