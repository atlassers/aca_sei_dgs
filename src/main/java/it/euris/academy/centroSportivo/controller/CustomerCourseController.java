package it.euris.academy.centroSportivo.controller;

import it.euris.academy.centroSportivo.data.dto.CustomerCourseDto;
import it.euris.academy.centroSportivo.repository.projection.CustomerCourseCountProjection;
import it.euris.academy.centroSportivo.service.CustomerCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

//TODO da testare se metodi funzionano
@RestController
@RequestMapping("/customer-courses")
public class CustomerCourseController {

    @Autowired CustomerCourseService customerCourseService;

    @GetMapping("/v1")
    public Set<CustomerCourseDto> getAll() {
        return customerCourseService.getAll();
    }

    @GetMapping("/v1/deleted")
    public Set<CustomerCourseDto> getAllDeleted() {
        return customerCourseService.getAllDeleted();
    }

    @GetMapping("/v1/all")
    public Set<CustomerCourseDto> getReallyAll() {
        return customerCourseService.getReallyAll();
    }

    @GetMapping("/v1/count")
    public CustomerCourseCountProjection getCount(){
        return customerCourseService.getCount();
    }

    @GetMapping("/v1/{customer-id}-{course-id}")
    public CustomerCourseDto getById(@PathVariable("customer-id") Long customerId, @PathVariable("course-id") Long courseId) {
        return customerCourseService.get(customerId, courseId);
    }

    @PostMapping("/v1")
    public CustomerCourseDto add(@RequestBody CustomerCourseDto customerCourseDto) {
        return customerCourseService.add(customerCourseDto);
    }

    @PutMapping("/v1")
    public CustomerCourseDto update(@RequestBody CustomerCourseDto customerCourseDto) {
        return customerCourseService.update(customerCourseDto);
    }

    @DeleteMapping("/v1/{customer-id}-{course-id}")
    public Boolean delete(@PathVariable("customer-id") Long customerId, @PathVariable("course-id") Long courseId) {
        return customerCourseService.delete(customerId, courseId);
    }
}
