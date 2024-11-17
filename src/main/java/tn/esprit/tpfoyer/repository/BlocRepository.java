package tn.esprit.tpfoyer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Bloc;

import java.util.List;














@Repository
 public interface BlocRepository extends JpaRepository<Bloc, Long> {




















  // Récupérer les Blocs qui ont une capacité supérieure à 50 :

   List<Bloc> findAllByCapaciteBlocGreaterThan(long c);
















   // Récupérer les Blocs qui ont une capacité inférieurs à 50 :
   // List<Bloc> findAllByCapaciteBlocLessThan(long c);


    // Récupérer tous les Blocs qui ont un nom qui commence par "Bl" :
    List<Bloc> findAllByNomBlocStartingWith(String c);


    // Récuprer tous les blocs qui ont un nom donné et une capacité donnée :
    List<Bloc> findAllByNomBlocAndCapaciteBloc (String nom , long capacite );


    // Récupérer le bloc qui a un nom donné :
    Bloc findByNomBloc (String nom);















    /* No need to code CRUD here. Its is already in the
    interfaces provided by the framework Spring Data JPA :
       - CrudRepository or
       - PagingAndSortingRepository or
       - JpaRepository
     */


    Bloc findBlocByNomBlocAndCapaciteBlocGreaterThan(String nb, long c);

    // List des blocs non affectés à aucun foyer :
    List<Bloc> findAllByFoyerIsNull();


    //Bloc findByC
    //findAllByFoyerIsNull();




}
