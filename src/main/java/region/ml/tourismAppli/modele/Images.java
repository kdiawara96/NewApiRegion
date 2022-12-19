package region.ml.tourismAppli.modele;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder

public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //----------------------fichier------------------------
    private String type;
    private String imagename;
    @Column(name="image", length =1000)
    private byte[] image;
    //----------------------end---------------------------

    @OneToOne(mappedBy = "image")
    private Region region;

    @OneToOne(mappedBy = "image")
    private  Utilisateurs utilisateurs;



}
