package region.ml.tourismAppli.modele;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private region.ml.tourismAppli.others.Roles role;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Utilisateurs> utilisateurs = new ArrayList<>();
}
