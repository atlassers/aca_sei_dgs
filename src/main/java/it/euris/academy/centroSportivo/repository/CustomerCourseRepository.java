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
            "  SET c.deleted=1 " +
            "  WHERE c.id = :id", nativeQuery = true)
    void logicalDelete(@Param("id") CustomerCourseKey id);

    @Query(value=   "SELECT COUNT(c.course_id) as countAll, " +
        " SUM(CASE WHEN c.deleted=0 THEN 1 ELSE 0 END) as countOk, " +
        " SUM(CASE WHEN c.deleted=1 THEN 1 ELSE 0 END) as countDeleted " +
        "  FROM customer_course c " +
        "  JOIN customer cu ON cu.id=c.customer_id " +
        "  JOIN course co ON co.id=c.course_id" +
        "  WHERE c.deleted=0 or c.deleted=1", nativeQuery = true)
    CustomerCourseCountProjection getCount();

    @Query(value =  "SELECT * " +
        "FROM customer_course c " +
        "  JOIN customer cu ON cu.id=c.customer_id " +
        "  JOIN course co ON co.id=c.course_id" +
        "  JOIN contact con ON con.customer_id=cu.id", nativeQuery = true)
    Set<CustomerCourse> getReallyAll();

    @Query(value =  "SELECT * " +
        "FROM customer_course c " +
        "  JOIN customer cu ON cu.id=c.customer_id " +
        "  JOIN course co ON co.id=c.course_id" +
        " WHERE c.deleted=1", nativeQuery = true)
    Set<CustomerCourse> getAllDeleted();
}
