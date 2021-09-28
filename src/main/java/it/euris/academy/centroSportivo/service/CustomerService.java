package it.euris.academy.centroSportivo.service;

import it.euris.academy.centroSportivo.data.dto.CustomerDto;
import it.euris.academy.centroSportivo.data.dto.CustomerDto;
import it.euris.academy.centroSportivo.repository.projection.CustomerCountProjection;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    Set<CustomerDto> getAll();

    CustomerDto get(Long id);

    CustomerDto add(CustomerDto dataToInsert);

    CustomerDto update(CustomerDto dataToInsert);

    Boolean delete(Long id);

    CustomerCountProjection getCount();

    Set<CustomerDto> getReallyAll();

    Set<CustomerDto> getAllDeleted();
}
