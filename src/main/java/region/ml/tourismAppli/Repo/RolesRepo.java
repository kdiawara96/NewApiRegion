package region.ml.tourismAppli.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import region.ml.tourismAppli.modele.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles,Long> {

    Roles findByRole (String role);
}
