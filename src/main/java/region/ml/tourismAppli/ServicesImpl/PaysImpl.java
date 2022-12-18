package region.ml.tourismAppli.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import region.ml.tourismAppli.Repo.PaysRepo;
import region.ml.tourismAppli.Services.PaysServices;
import region.ml.tourismAppli.modele.Pays;

import java.util.List;

@Service
public class PaysImpl implements PaysServices {

    @Autowired
    private PaysRepo repo;
    @Override
    public Pays creat(Pays pays) {
        return repo.save(pays);
    }

    @Override
    public List<Pays> readCountry(Pays pays) {
        return repo.findAll();
    }
}
