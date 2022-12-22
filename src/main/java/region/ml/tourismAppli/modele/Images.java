package region.ml.tourismAppli.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="image", length =100000)
    private byte[] image;
    //----------------------end---------------------------

    @JsonIgnore
    @OneToOne(mappedBy = "image")
    private Region region;

    @JsonIgnore
    @OneToOne(mappedBy = "image")
    private  Utilisateurs utilisateurs;

    @JsonIgnore
    @OneToOne(mappedBy = "image")
    private Pays pays;




}
