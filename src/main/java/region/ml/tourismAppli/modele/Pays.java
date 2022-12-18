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

public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom",length = 50, unique = true, nullable = false)
    private String nom;
    //Un pays peut avoir une ou plusieurs région
    @OneToMany(mappedBy = "pays", cascade = CascadeType.ALL)
    private Collection<Region> region = new ArrayList<>();






}
