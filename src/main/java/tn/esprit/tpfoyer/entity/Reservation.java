package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    String idReservation;


    Date anneeUniversitaire;
    boolean estValide;











    @ManyToMany
    Set<Etudiant> etudiants;




    /*@ToString.Exclude
    @JsonIgnore*/

}


