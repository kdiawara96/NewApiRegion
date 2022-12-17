package region.ml.tourismAppli.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;
    private String email;
    private String password;

   @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private Collection<Commentaires> commentaires = new ArrayList<>();


    @ManyToMany(mappedBy = "utilisateurs",fetch = FetchType.EAGER)
    private Set<Roles> roles = new HashSet<>();

}
