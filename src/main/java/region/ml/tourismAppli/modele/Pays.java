package region.ml.tourismAppli.modele;


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

    @Column(name="nompays",length = 50,unique = true, nullable = false)
    private String nompays;

    //Un pays peut avoir une ou plusieurs région
    @OneToMany(mappedBy = "pays")
    private Collection<Region> region = new ArrayList<>();






}
