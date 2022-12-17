package region.ml.tourismApi.modele;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "pays")
@Table(name = "pays")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nompays",length = 50,unique = true, nullable = false)
    private String nompays;




}
