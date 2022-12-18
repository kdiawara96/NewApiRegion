package region.ml.tourismAppli.modele;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import region.ml.tourismAppli.others.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Enumerated(EnumType.ORDINAL)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    List<Utilisateurs> utilisateur = new ArrayList<>();
}
