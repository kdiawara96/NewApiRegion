package region.ml.tourismAppli.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.Repo.ImagesRepo;
import region.ml.tourismAppli.Services.ImagesService;
import region.ml.tourismAppli.modele.Images;
import region.ml.tourismAppli.modele.Region;
import region.ml.tourismAppli.others.imageUpload;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImagesImpl implements ImagesService {

    @Autowired
    private ImagesRepo repo;


    //Pour sauvegarder l'image

    @Override
    public Images saveImage(MultipartFile file) throws IOException {

        try {
            return repo.save( Images.builder()
                    .imagename(file.getOriginalFilename())
                    .type(file.getContentType())
                    .image(imageUpload.compression(file.getBytes())).build()

            );
        }catch (Exception e){
            return null;
        }



    }


    //Pour telecharger l'image
    public byte[] downloadImage(String fileName) {
        Optional<Images> dbImageData = repo.findByImagename(fileName);
        byte[] images = imageUpload.decompression(dbImageData.get().getImage());

        return images;
    }



}
