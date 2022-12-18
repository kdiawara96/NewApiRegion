package region.ml.tourismAppli.Services;


import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Utilisateurs;

import java.util.List;

public interface PaysServices {

    Pays creat(Pays pays);



    List<Pays> readCountry(Pays pays);


}
