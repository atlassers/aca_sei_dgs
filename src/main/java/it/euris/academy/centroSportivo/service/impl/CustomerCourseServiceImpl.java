package it.euris.academy.centroSportivo.service.impl;

import it.euris.academy.centroSportivo.data.dto.CustomerCourseDto;
import it.euris.academy.centroSportivo.data.model.CustomerCourse;
import it.euris.academy.centroSportivo.data.model.key.CustomerCourseKey;
import it.euris.academy.centroSportivo.exception.IdMustBeNullException;
import it.euris.academy.centroSportivo.exception.IdMustNotBeNullException;
import it.euris.academy.centroSportivo.repository.CustomerCourseRepository;
import it.euris.academy.centroSportivo.repository.projection.CustomerCourseCountProjection;
import it.euris.academy.centroSportivo.service.CustomerCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerCourseServiceImpl implements CustomerCourseService {

    @Autowired
    CustomerCourseRepository customerCourseRepository;

    @Override
    public Set<CustomerCourseDto> getAll() {
        return customerCourseRepository.findAll().stream()
                .map(CustomerCourse::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CustomerCourseDto get(Long customerId, Long courseId) {
        CustomerCourseKey id = new CustomerCourseKey(customerId, courseId);
        Optional<CustomerCourse> gotCustomerCourse = customerCourseRepository.findById(id);
        return gotCustomerCourse.map(CustomerCourse::toDto).orElse(null);
    }

    @Override
    public CustomerCourseDto add(CustomerCourseDto dataToInsert) {
        if(dataToInsert.getId() != null)
            throw new IdMustBeNullException();
        return customerCourseRepository.save(dataToInsert.toModel()).toDto();
    }

    @Override
    public CustomerCourseDto update(CustomerCourseDto dataToInsert) {
        if(dataToInsert.getId() == null)
            throw new IdMustNotBeNullException();
        return customerCourseRepository.save(dataToInsert.toModel()).toDto();
    }

    @Override
    public Boolean delete(Long customerId, Long courseId) {
        CustomerCourseKey id = new CustomerCourseKey(customerId, courseId);
        customerCourseRepository.logicalDelete(id);
        return customerCourseRepository.findById(id).get().getDeleted();
    }

    @Override public CustomerCourseCountProjection getCount() {
        return customerCourseRepository.getCount();
    }

    @Override public Set<CustomerCourseDto> getReallyAll() {
        return customerCourseRepository.getReallyAll().stream().map(CustomerCourse::toDto).collect(Collectors.toSet());
    }

    @Override public Set<CustomerCourseDto> getAllDeleted() {
        return customerCourseRepository.getAllDeleted().stream().map(CustomerCourse::toDto).collect(Collectors.toSet());
    }
}
