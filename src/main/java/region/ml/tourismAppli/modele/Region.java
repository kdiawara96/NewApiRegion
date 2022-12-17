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

public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name="nomregion",length = 25,unique = true)
    private String nomregion;

    @Column( name="codereegion",length = 10, unique = true, nullable = false)
    private String coderegion;

    @Column(length = 50)
    private String domaineactivite;
    @Column(length = 50)
    private String superficie;
    @Column(length = 50)
    private String langue;


    //Une région concerne un et un seul pays
    @ManyToOne
    private Pays pays;

    //une région peut avoir 0 ou plusieurs commentaires
    @JsonIgnore
    @OneToMany(mappedBy = "region")
    private Collection<Commentaires> commentaire = new ArrayList<>();


   
}
