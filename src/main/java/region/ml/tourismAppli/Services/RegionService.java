package region.ml.tourismAppli.Services;


import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Region;
import region.ml.tourismAppli.modele.Utilisateurs;

import java.io.IOException;
import java.util.List;

public interface RegionService {


    Pays getById(Long id);

    Region create(Region region);

    List<Region> readRegionByCountry(Long id);

    Region update(Region region);

    String delete(Long id);


}
