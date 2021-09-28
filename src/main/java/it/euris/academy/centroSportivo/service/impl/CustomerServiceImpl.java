package it.euris.academy.centroSportivo.service.impl;

import it.euris.academy.centroSportivo.data.dto.CustomerDto;
import it.euris.academy.centroSportivo.data.model.Customer;
import it.euris.academy.centroSportivo.exception.IdMustBeNullException;
import it.euris.academy.centroSportivo.exception.IdMustNotBeNullException;
import it.euris.academy.centroSportivo.repository.CustomerRepository;
import it.euris.academy.centroSportivo.repository.projection.CustomerCountProjection;
import it.euris.academy.centroSportivo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Set<CustomerDto> getAll() {
        return customerRepository.findAll().stream()
                .map(Customer::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CustomerDto get(Long id) {
        Optional<Customer> gotCustomer = customerRepository.findById(id);
        return gotCustomer.map(Customer::toDto).orElse(null);
    }

    @Override
    public CustomerDto add(CustomerDto dataToInsert) {
        if(dataToInsert.getId() != null)
            throw new IdMustBeNullException();
        return customerRepository.save(dataToInsert.toModel()).toDto();
    }

    @Override
    public CustomerDto update(CustomerDto dataToInsert) {
        if(dataToInsert.getId() == null)
            throw new IdMustNotBeNullException();
        return customerRepository.save(dataToInsert.toModel()).toDto();
    }

    @Override
    public Boolean delete(Long id) {
        customerRepository.logicalDelete(id);
        return customerRepository.findById(id).get().getDeleted();
    }

    @Override
    public CustomerCountProjection getCount(){
        return customerRepository.getCount();
    }

    @Override
    public Set<CustomerDto> getReallyAll() {
        return customerRepository.getReallyAll().stream().map(Customer::toDto).collect(Collectors.toSet());
    }

    @Override
    public Set<CustomerDto> getAllDeleted() {
        return customerRepository.getAllDeleted().stream().map(Customer::toDto).collect(Collectors.toSet());
    }
}
