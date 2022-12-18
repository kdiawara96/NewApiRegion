package region.ml.tourismAppli.modele;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commentaires {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String description;

    private int aime = 0;


   //Un commentaire est fait par un et un seul utilisateur
    @ManyToOne
    private Utilisateurs utilisateur;

    //un commentaire concerne une et une seule région
    @ManyToOne
    private Region region;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
