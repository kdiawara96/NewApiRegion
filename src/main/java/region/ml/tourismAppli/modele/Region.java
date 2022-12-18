package region.ml.tourismAppli.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(length = 25,unique = true, nullable = false)
    private String nomregion;

    @Column(length = 10, unique = true, nullable = false)
    private String coderegion;

    @Column(length = 50, nullable = false)
    private String domaineactivite;
    @Column(length = 50, nullable = false)
    private String superficie;
    @Column(length = 50,nullable = false)
    private String langue;

    //----------------------fichier------------------------
    private String type;
    private String imagename;
    private byte[] imageRegion;
    //----------------------end---------------------------

    //Une région concerne un et un seul pays
    @ManyToOne
    private Pays pays;

    //une région peut avoir 0 ou plusieurs commentaires
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL )
    private Collection<Commentaires> commentaire = new ArrayList<>();


   
}
