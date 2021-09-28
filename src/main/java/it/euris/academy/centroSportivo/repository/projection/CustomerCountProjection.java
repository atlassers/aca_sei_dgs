package it.euris.academy.centroSportivo.repository.projection;

public interface CustomerCountProjection {

    Integer getCountAll();

    Integer getCountDeleted();
}
