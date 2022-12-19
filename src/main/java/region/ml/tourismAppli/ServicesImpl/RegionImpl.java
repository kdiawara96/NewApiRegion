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

        List<Region> allRegion = repo.findAll();

        /*System.err.println("-------------------------------------------------------");
        System.err.println(allRegion);*/

        //nous créons un tableau dynamique de Region qui va englober tout les regions du pays donner
        List<Region> CartonRegions = new ArrayList<>();

        //Nous allons itterer sur l'ensemble des region (allRegion)
        for (Region region : allRegion) {

            System.err.println("------------------------------------------------------------------------");
            System.err.println("--------------"+pays.getId()+"---------------");
            System.err.println("--------------"+region.getPays().getId()+"---------------");
            try {
                //Nous allons prendre chaque region et verifier si le id du pays = au id du pays dans la region
                //et si ces true nous allons l'ajouter dans le tableau et au final renvoyer le tableau
                if (region.getPays().getId().equals(pays.getId())) {

                    System.err.println("------------------------------------------------------------------------");

                    System.err.println("--------------La taille = "+CartonRegions.size()+"---------------");

                    CartonRegions.add(region);


                    System.err.println("--------------"+ CartonRegions.get(0).getNomregion()+"---------------");

                }

            } catch (Exception e) {
                System.out.println(e);
            }

        }

        //la liste des régions par rapport a un pays donner
        return CartonRegions;
        //return repo.findByPays(pays.getId());
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
