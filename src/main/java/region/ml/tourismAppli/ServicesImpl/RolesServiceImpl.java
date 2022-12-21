package region.ml.tourismAppli.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import region.ml.tourismAppli.Repo.RolesRepo;
import region.ml.tourismAppli.Repo.UtilisateursRepo;
import region.ml.tourismAppli.Services.RolesService;
import region.ml.tourismAppli.modele.Roles;
import region.ml.tourismAppli.modele.Utilisateurs;

import java.util.HashSet;
import java.util.Set;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepo rolesRepo;
    @Autowired
    private UtilisateursRepo utilisateursRepo;

    @Override
    public Roles addRole(Roles role) {
        return rolesRepo.save(role);
    }

    @Override
    public void AddRoleToUser(String email, String role) {

        Utilisateurs user = utilisateursRepo.findByEmail(email);
        Roles roles = rolesRepo.findByRole(role);
        Set<Roles> roleee = new HashSet<>();
        roleee.add(roles);
        user.setRole(roleee);
    }

    @Override
    public Roles findbyRole(String role) {
        return rolesRepo.findByRole(role);
    }
}
