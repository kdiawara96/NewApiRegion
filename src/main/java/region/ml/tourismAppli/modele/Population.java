package region.ml.tourismAppli.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Population {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="habitant",length = 12, nullable = false)
    private int habitant;

    @Column(name="annee", nullable = false)
    private String annee;

    //Une population concerne une et une seule région
    @JsonIgnore
    @ManyToOne
    private Region region;

}
