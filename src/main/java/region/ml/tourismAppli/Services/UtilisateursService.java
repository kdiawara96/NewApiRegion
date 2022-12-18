package region.ml.tourismAppli.Services;

import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.modele.Utilisateurs;


public interface UtilisateursService {

    Utilisateurs create(MultipartFile file, Utilisateurs utilisateur);

    Utilisateurs update(Utilisateurs utilisateur);

    String delete(Long id);

    Utilisateurs getById(Long id);

    Utilisateurs login(String email, String password);


}
