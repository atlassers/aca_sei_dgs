package it.euris.academy.centroSportivo.repository;

import it.euris.academy.centroSportivo.data.model.Course;
import it.euris.academy.centroSportivo.data.model.Course;
import it.euris.academy.centroSportivo.repository.projection.CourseCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
    @Modifying
    @Transactional
    @Query(value=   "UPDATE course c " +
            "  SET c.deleted=1 " +
            "  WHERE c.id = :id", nativeQuery = true)
    void logicalDelete(@Param("id") Long id);

    @Query(value=   "SELECT COUNT(c.id) as countAll, " +
                    " SUM(CASE WHEN c.deleted=0 THEN 1 ELSE 0 END) as countOk " +
                    ", SUM(CASE WHEN c.deleted=1 THEN 1 ELSE 0 END) as countDeleted " +
            "  FROM course c " +
            "  WHERE c.deleted=0 or c.deleted=1", nativeQuery = true)
    CourseCountProjection getCount();

    @Query(value =  "SELECT * " +
            "FROM course c", nativeQuery = true)
    Set<Course> getReallyAll();

    @Query(value =  "SELECT * " +
            "FROM course c " +
            "WHERE c.deleted=1", nativeQuery = true)
    Set<Course> getAllDeleted();
}
