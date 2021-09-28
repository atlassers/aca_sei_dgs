package it.euris.academy.centroSportivo.controller;

import it.euris.academy.centroSportivo.data.dto.AddressDto;
import it.euris.academy.centroSportivo.repository.projection.AddressCountProjection;
import it.euris.academy.centroSportivo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/v1")
    public Set<AddressDto> getAll() {
        return addressService.getAll();
    }

    @GetMapping("/v1/deleted")
    public Set<AddressDto> getAllDeleted() {
        return addressService.getAllDeleted();
    }

    @GetMapping("/v1/all")
    public Set<AddressDto> getReallyAll() {
        return addressService.getReallyAll();
    }

    @GetMapping("/v1/count")
    public AddressCountProjection getCount(){
        return addressService.getCount();
    }

    @GetMapping("/v1/{id}")
    public AddressDto getById(@PathVariable("id") Long id) {
        return addressService.get(id);
    }

    @PostMapping("/v1")
    public AddressDto add(@RequestBody AddressDto addressDto) {
        return addressService.add(addressDto);
    }

    @PutMapping("/v1")
    public AddressDto update(@RequestBody AddressDto addressDto) {
        return addressService.update(addressDto);
    }

    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return addressService.delete(id);
    }
}
