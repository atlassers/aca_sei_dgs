package it.euris.academy.centroSportivo.service;

import it.euris.academy.centroSportivo.data.dto.AddressDto;
import it.euris.academy.centroSportivo.repository.projection.AddressCountProjection;
import java.util.Set;

public interface AddressService {

    Set<AddressDto> getAll();

    AddressDto get(Long id);

    AddressDto add(AddressDto dataToInsert);

    AddressDto update(AddressDto dataToInsert);

    Boolean delete(Long id);

    AddressCountProjection getCount();

    Set<AddressDto> getReallyAll();

    Set<AddressDto> getAllDeleted();
}
