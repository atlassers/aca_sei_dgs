package it.euris.academy.centroSportivo.repository.projection;

public interface ContactCountProjection {
    Integer getCountAll();

    Integer getCountOk();

    Integer getCountDeleted();
}
