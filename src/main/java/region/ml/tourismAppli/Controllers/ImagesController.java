package region.ml.tourismAppli.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.Services.ImagesService;
import region.ml.tourismAppli.modele.Region;
import region.ml.tourismAppli.others.Message;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImagesController {

    @Autowired
    private ImagesService service;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {


        return ResponseEntity.status(HttpStatus.OK).body(service.saveImage(file));
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
       byte[] image = service.downloadImage(fileName);

       return ResponseEntity.status(HttpStatus.OK)
               .contentType(MediaType.valueOf("image/png"))
               .body(image);
    }
}
