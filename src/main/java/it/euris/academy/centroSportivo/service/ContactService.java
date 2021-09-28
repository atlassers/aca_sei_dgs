package it.euris.academy.centroSportivo.service;

import it.euris.academy.centroSportivo.data.dto.ContactDto;
import it.euris.academy.centroSportivo.repository.projection.ContactCountProjection;
import java.util.Set;

public interface ContactService {
    Set<ContactDto> getAll();

    ContactDto get(Long id);

    ContactDto add(ContactDto dataToInsert);

    ContactDto update(ContactDto dataToInsert);

    Boolean delete(Long id);

    ContactCountProjection getCount();

    Set<ContactDto> getReallyAll();

    Set<ContactDto> getAllDeleted();
}
