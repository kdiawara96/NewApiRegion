package region.ml.tourismAppli.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import region.ml.tourismAppli.others.Role;

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
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private Collection<Commentaires> commentaires = new ArrayList<>();


    @ManyToOne
    private Roles role;

}
