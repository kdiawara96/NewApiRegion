package region.ml.tourismAppli.Services;

import org.springframework.web.multipart.MultipartFile;
import region.ml.tourismAppli.modele.Images;

import java.io.IOException;

public interface ImagesService {
    Images saveImage(MultipartFile file) throws IOException;

    public byte[] downloadImage(String fileName);
}
