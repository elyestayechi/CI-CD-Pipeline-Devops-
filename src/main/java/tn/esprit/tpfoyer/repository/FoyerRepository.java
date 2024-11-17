package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Foyer;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long>
{

    /* No need to code CRUD here. Its is already in the
    interfaces provided by the framework Spring Data JPA :
       - CrudRepository or
       - PagingAndSoringRepository or
       - JpaRepository
     */

    /* Keywords : */


}
