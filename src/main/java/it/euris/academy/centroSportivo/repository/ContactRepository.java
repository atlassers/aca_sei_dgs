package it.euris.academy.centroSportivo.repository;

import it.euris.academy.centroSportivo.data.model.Contact;
import it.euris.academy.centroSportivo.repository.projection.ContactCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    @Modifying
    @Transactional
    @Query(value=   "UPDATE contact c " +
            "  SET c.contact=1 " +
            "  WHERE c.id = :id", nativeQuery = true)
    void logicalDelete(@Param("id") Long id);

    @Query(value=   "SELECT COUNT(c.id) as countAll, " +
        " SUM(CASE WHEN c.deleted=0 THEN 1 ELSE 0 END) as countOk " +
        ", SUM(CASE WHEN c.deleted=1 THEN 1 ELSE 0 END) as countDeleted " +
            "  FROM contact c " +
            "  WHERE c.deleted=0 or c.deleted=1", nativeQuery = true)
    ContactCountProjection getCount();

    @Query(value =  "SELECT * " +
            "FROM contact c", nativeQuery = true)
    Set<Contact> getReallyAll();

    @Query(value =  "SELECT * " +
            "FROM contact c " +
            "WHERE c.deleted=1", nativeQuery = true)
    Set<Contact> getAllDeleted();
}
