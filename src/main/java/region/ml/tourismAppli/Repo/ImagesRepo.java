package region.ml.tourismAppli.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import region.ml.tourismAppli.modele.Images;
import region.ml.tourismAppli.modele.Region;

import java.util.Optional;

@Repository
public interface ImagesRepo extends JpaRepository<Images,Long> {

    Optional<Images> findByImagename(String fileName);
}
