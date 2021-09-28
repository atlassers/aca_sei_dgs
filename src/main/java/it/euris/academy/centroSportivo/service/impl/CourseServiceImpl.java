package it.euris.academy.centroSportivo.service.impl;

import it.euris.academy.centroSportivo.data.dto.CourseDto;
import it.euris.academy.centroSportivo.data.dto.CourseDto;
import it.euris.academy.centroSportivo.data.model.Course;
import it.euris.academy.centroSportivo.data.model.Course;
import it.euris.academy.centroSportivo.exception.IdMustBeNullException;
import it.euris.academy.centroSportivo.exception.IdMustNotBeNullException;
import it.euris.academy.centroSportivo.repository.CourseRepository;
import it.euris.academy.centroSportivo.repository.projection.CourseCountProjection;
import it.euris.academy.centroSportivo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    
    @Autowired
    CourseRepository courseRepository;

    @Override
    public Set<CourseDto> getAll() {
        return courseRepository.findAll().stream()
                .map(Course::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CourseDto get(Long id) {
        Optional<Course> gotCourse = courseRepository.findById(id);
        return gotCourse.map(Course::toDto).orElse(null);
    }

    @Override
    public CourseDto add(CourseDto dataToInsert) {
        if(dataToInsert.getId() != null)
            throw new IdMustBeNullException();
        return courseRepository.save(dataToInsert.toModel()).toDto();
    }

    @Override
    public CourseDto update(CourseDto dataToInsert) {
        if(dataToInsert.getId() == null)
            throw new IdMustNotBeNullException();
        return courseRepository.save(dataToInsert.toModel()).toDto();
    }

    @Override
    public Boolean delete(Long id) {
        courseRepository.logicalDelete(id);
        return courseRepository.findById(id).get().getDeleted();
    }

    @Override
    public CourseCountProjection getCount(){
        return courseRepository.getCount();
    }

    @Override
    public Set<CourseDto> getReallyAll() {
        return courseRepository.getReallyAll().stream().map(Course::toDto).collect(Collectors.toSet());
    }

    @Override
    public Set<CourseDto> getAllDeleted() {
        return courseRepository.getAllDeleted().stream().map(Course::toDto).collect(Collectors.toSet());
    }
}
