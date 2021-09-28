package it.euris.academy.centroSportivo.repository;

import it.euris.academy.centroSportivo.data.model.Address;
import it.euris.academy.centroSportivo.data.model.CustomerCourse;
import it.euris.academy.centroSportivo.data.model.key.CustomerCourseKey;
import it.euris.academy.centroSportivo.repository.projection.AddressCountProjection;
import it.euris.academy.centroSportivo.repository.projection.CustomerCourseCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface CustomerCourseRepository extends JpaRepository<CustomerCourse, CustomerCourseKey> {

    @Modifying
    @Transactional
    @Query(value=   "UPDATE customer_course c " +
            "  SET c.deleted=true " +
            "  WHERE c.id = :id", nativeQuery = true)
    void logicalDelete(@Param("id") CustomerCourseKey id);

    @Query(value=   "SELECT COUNT(c.id) as countAll, COUNT(c.deleted) as countDeleted " +
        "  FROM customer_course c " +
        "  WHERE c.deleted=0 or c.deleted=1", nativeQuery = true)
    CustomerCourseCountProjection getCount();

    @Query(value =  "SELECT * " +
        "FROM customer_course c", nativeQuery = true)
    Set<CustomerCourse> getReallyAll();

    @Query(value =  "SELECT * " +
        "FROM customer_course c " +
        "WHERE c.deleted=true", nativeQuery = true)
    Set<CustomerCourse> getAllDeleted();
}
