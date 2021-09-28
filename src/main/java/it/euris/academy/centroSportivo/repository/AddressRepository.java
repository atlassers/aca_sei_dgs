package it.euris.academy.centroSportivo.repository;

import it.euris.academy.centroSportivo.data.model.Address;
import it.euris.academy.centroSportivo.repository.projection.AddressCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Modifying
    @Transactional
    @Query(value=   "UPDATE address a " +
                    "  SET a.deleted=true " +
                    "  WHERE a.id = :id", nativeQuery = true)
    void logicalDelete(@Param("id") Long id);

    @Query(value=   "SELECT COUNT(a.id) as countAll, COUNT(a.deleted) as countDeleted " +
                    "  FROM address a " +
                    "  WHERE a.deleted=0 or a.deleted=1", nativeQuery = true)
    AddressCountProjection getCount();

    @Query(value =  "SELECT * " +
                    "FROM address a", nativeQuery = true)
    Set<Address> getReallyAll();

    @Query(value =  "SELECT * " +
                    "FROM address a " +
                    "WHERE a.deleted=true", nativeQuery = true)
    Set<Address> getAllDeleted();
}