package it.euris.academy.centroSportivo.repository.projection;

public interface AddressCountProjection {

    Integer getCountAll();

    Integer getCountOk();

    Integer getCountDeleted();
}
