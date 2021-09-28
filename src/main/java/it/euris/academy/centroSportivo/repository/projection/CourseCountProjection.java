package it.euris.academy.centroSportivo.repository.projection;

public interface CourseCountProjection {

    Integer getCountAll();

    Integer getCountOk();

    Integer getCountDeleted();
}
