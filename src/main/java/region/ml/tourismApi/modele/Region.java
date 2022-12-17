package region.ml.tourismApi.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "region")
@Table(name = "region")
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

    @ManyToOne
    private Pays pays;


   // @OneToMany(mappedBy = "region")
 // private Collection<Population> population;

   
}
