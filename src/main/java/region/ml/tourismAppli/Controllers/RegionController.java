package region.ml.tourismAppli.Controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.Repo.RegionRepo;
import org.springframework.security.core.Authentication;
import region.ml.tourismAppli.Services.ImagesService;
import region.ml.tourismAppli.Services.RegionService;
import region.ml.tourismAppli.modele.Images;
import region.ml.tourismAppli.modele.Region;
import region.ml.tourismAppli.others.Message;


@AllArgsConstructor
@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService service;

    @Autowired
    final private RegionRepo regionRepo;

    @Autowired
    private ImagesService imageService;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> createRegion(Authentication auauthentication, @RequestParam("image") MultipartFile file, @RequestParam("data") String region){



        try {
            Images img =  imageService.saveImage(file);

            Region rgn = new Region();

            rgn = new JsonMapper().readValue(region, Region.class);

            rgn.setImage(img);

            Region rg = service.create(rgn);

           return Message.Response("ok", HttpStatus.OK,rg);
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
