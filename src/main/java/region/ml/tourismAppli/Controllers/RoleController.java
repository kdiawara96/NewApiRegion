package region.ml.tourismAppli.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import region.ml.tourismAppli.Services.RolesService;
import region.ml.tourismAppli.modele.Roles;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RolesService service;


    @PostMapping("/create_role")
  //  @PreAuthorize("hasAuthority('SCOPE_ADMIN')") Authentication auauthentication,
    public Roles createRole(@RequestBody Roles role){
        System.err.println(role);
        return service.addRole(role);
    }
}
