package it.euris.academy.centroSportivo.service;

import it.euris.academy.centroSportivo.data.dto.CourseDto;
import it.euris.academy.centroSportivo.repository.projection.CourseCountProjection;
import java.util.Set;

public interface CourseService {
    Set<CourseDto> getAll();

    CourseDto get(Long id);

    CourseDto add(CourseDto dataToInsert);

    CourseDto update(CourseDto dataToInsert);
     
    Boolean delete(Long id);

    CourseCountProjection getCount();

    Set<CourseDto> getReallyAll();

    Set<CourseDto> getAllDeleted();
}
