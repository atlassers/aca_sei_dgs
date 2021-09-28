package it.euris.academy.centroSportivo.service;

import it.euris.academy.centroSportivo.data.dto.AddressDto;
import it.euris.academy.centroSportivo.data.dto.CustomerCourseDto;
import it.euris.academy.centroSportivo.data.model.key.CustomerCourseKey;
import it.euris.academy.centroSportivo.repository.projection.AddressCountProjection;
import it.euris.academy.centroSportivo.repository.projection.CustomerCourseCountProjection;

import java.util.List;
import java.util.Set;

public interface CustomerCourseService {
//TODO da controllare queste firme di metodi e vedere come implementarne altri
    Set<CustomerCourseDto> getAll();

    CustomerCourseDto get(Long customerId, Long courseId);

    CustomerCourseDto add(CustomerCourseDto dataToInsert);

    CustomerCourseDto update(CustomerCourseDto dataToInsert);

    Boolean delete(Long customerId, Long courseId);

    CustomerCourseCountProjection getCount();

    Set<CustomerCourseDto> getReallyAll();

    Set<CustomerCourseDto> getAllDeleted();
}
