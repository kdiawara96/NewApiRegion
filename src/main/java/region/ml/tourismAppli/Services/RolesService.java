package region.ml.tourismAppli.Services;

import region.ml.tourismAppli.modele.Roles;

public interface RolesService {

    public Roles addRole(Roles role);

    void AddRoleToUser(String email, String role);

    public Roles findbyRole(String role);


}
