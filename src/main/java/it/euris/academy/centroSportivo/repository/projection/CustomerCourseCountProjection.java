package it.euris.academy.centroSportivo.repository.projection;

public interface CustomerCourseCountProjection {

    Integer getCountAll();

    Integer getCountOk();

    Integer getCountDeleted();
}
