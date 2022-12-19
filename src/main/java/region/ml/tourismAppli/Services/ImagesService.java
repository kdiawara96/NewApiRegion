package region.ml.tourismAppli.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImagesService {
    String saveImage(MultipartFile file) throws IOException;

    public byte[] downloadImage(String fileName);
}
