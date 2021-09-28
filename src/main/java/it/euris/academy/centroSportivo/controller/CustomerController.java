package it.euris.academy.centroSportivo.controller;

import it.euris.academy.centroSportivo.data.dto.CustomerDto;
import it.euris.academy.centroSportivo.repository.projection.CustomerCountProjection;
import it.euris.academy.centroSportivo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/v1")
    public Set<CustomerDto> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/v1/deleted")
    public Set<CustomerDto> getAllDeleted() {
        return customerService.getAllDeleted();
    }

    @GetMapping("/v1/all")
    public Set<CustomerDto> getReallyAll() {
        return customerService.getReallyAll();
    }

    @GetMapping("/v1/count")
    public CustomerCountProjection getCount(){
        return customerService.getCount();
    }

    @GetMapping("/v1/{id}")
    public CustomerDto getById(@PathVariable("id") Long id) {
        return customerService.get(id);
    }

    @PostMapping("/v1")
    public CustomerDto add(@RequestBody CustomerDto customerDto) {
        return customerService.add(customerDto);
    }

    @PutMapping("/v1")
    public CustomerDto update(@RequestBody CustomerDto customerDto) {
        return customerService.update(customerDto);
    }

    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return customerService.delete(id);
    }
}
