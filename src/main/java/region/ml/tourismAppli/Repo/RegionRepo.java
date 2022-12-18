package region.ml.tourismAppli.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Region;
import region.ml.tourismAppli.modele.Utilisateurs;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepo extends JpaRepository<Region, Long> {

  //  public Optional<Region> findById(Long id);
    Region findByCoderegion(String codeRegion);

    List<Region> findByPays(Pays pays);
 }
