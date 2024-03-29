package region.ml.tourismAppli.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.Services.ImagesService;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImagesController {

    @Autowired
    private ImagesService service;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> uploadImage(Authentication auauthentication, @RequestParam("image") MultipartFile file) throws IOException {


        return ResponseEntity.status(HttpStatus.OK).body(service.saveImage(file));
    }

    @GetMapping("/{fileName}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> downloadImage( Authentication auauthentication, @PathVariable String fileName){
       byte[] image = service.downloadImage(fileName);

       return ResponseEntity.status(HttpStatus.OK)
               .contentType(MediaType.valueOf("image/png"))
               .body(image);
    }
}
