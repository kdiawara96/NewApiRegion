package region.ml.tourismAppli.Controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.Repo.RegionRepo;
import org.springframework.security.core.Authentication;
import region.ml.tourismAppli.Services.ImagesService;
import region.ml.tourismAppli.Services.PaysServices;
import region.ml.tourismAppli.Services.RegionService;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Region;
import region.ml.tourismAppli.others.Message;

import java.io.Console;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService service;

    @Autowired
    final private RegionRepo regionRepo;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> createRegion(Authentication auauthentication, @RequestBody Region region){

        try {
           return Message.Response("ok", HttpStatus.OK,service.create(region) );
        }catch(Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Erreur d'insertion de region!");
        }
    }


    @GetMapping("/readRegionByCountry/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public ResponseEntity<Object> readRegionByCountry( Authentication auauthentication, @PathVariable Long id){
        try {
            return Message.Response("ok", HttpStatus.OK, service.readRegionByCountry(id));
        }catch (Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Error to listing");
        }

    }


 /*   @GetMapping("/readRegionByCountry/{id}")
    public List<Region> readRegionByCountry(@PathVariable("id") Long id){

       return service.readRegionByCountry(id);
    }
*/



}
