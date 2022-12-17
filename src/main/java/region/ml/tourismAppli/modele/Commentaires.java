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
    private Long id;

    @Column(name = "description")
    private String description;

    private int like = 0;


   //Un commentaire est fait par un et un seul utilisateur
    @ManyToOne
    private Utilisateurs utilisateur;

    //un commentaire concerne une et une seule région
    @ManyToOne
    private Region region;
}
