package region.ml.tourismAppli.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import region.ml.tourismAppli.Services.PaysServices;
import region.ml.tourismAppli.Services.RegionService;
import region.ml.tourismAppli.modele.Pays;
import region.ml.tourismAppli.modele.Region;
import region.ml.tourismAppli.others.Message;

@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService service;

    private PaysServices p;


    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Object> createRegion(@RequestBody Region region){

        try {
           return Message.Response("ok", HttpStatus.OK,service.create(region) );
        }catch(Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Erreur d'insertion de region!");
        }
    }










    @GetMapping("/readRegionByCountry")
    public ResponseEntity<Object> readRegionByCountry(@RequestBody Pays pays){
        try {
            return Message.Response("ok", HttpStatus.OK,service.readRegionByCountry(pays));
        }catch (Exception e){
            return Message.Response("none", HttpStatus.BAD_REQUEST,"Error to listing");
        }

    }
}