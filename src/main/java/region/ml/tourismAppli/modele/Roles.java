package region.ml.tourismAppli.modele;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Enumerated(EnumType.STRING)
    private String role;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "UserRole", joinColumns = {
            @JoinColumn(name = "id_role") }, inverseJoinColumns = {
            @JoinColumn(name = "id_utilisateur") })
   private Collection<Utilisateurs> utilisateur = new ArrayList<>();
}
