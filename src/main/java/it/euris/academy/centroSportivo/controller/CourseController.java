package it.euris.academy.centroSportivo.controller;

import it.euris.academy.centroSportivo.data.dto.CourseDto;
import it.euris.academy.centroSportivo.repository.projection.CourseCountProjection;
import it.euris.academy.centroSportivo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/v1")
    public Set<CourseDto> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/v1/deleted")
    public Set<CourseDto> getAllDeleted() {
        return courseService.getAllDeleted();
    }

    @GetMapping("/v1/all")
    public Set<CourseDto> getReallyAll() {
        return courseService.getReallyAll();
    }

    @GetMapping("/v1/count")
    public CourseCountProjection getCount(){
        return courseService.getCount();
    }

    @GetMapping("/v1/{id}")
    public CourseDto getById(@PathVariable("id") Long id) {
        return courseService.get(id);
    }

    @PostMapping("/v1")
    public CourseDto add(@RequestBody CourseDto courseDto) {
        return courseService.add(courseDto);
    }

    @PutMapping("/v1")
    public CourseDto update(@RequestBody CourseDto courseDto) {
        return courseService.update(courseDto);
    }

    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return courseService.delete(id);
    }
}
