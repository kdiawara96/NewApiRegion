package region.ml.tourismAppli.Services;


import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Region;
import region.ml.tourismAppli.modele.Utilisateurs;

import java.util.List;

public interface RegionService {

   // Region create(Region region);

    Region create(Region region);

    Pays getById(Long id);


    List<Region> readRegionByCountry(Pays pays);

    Region update(Region region);

    String delete(Long id);



    //List<Region> readRegionByCountry(Pays pays, Long id);
}
