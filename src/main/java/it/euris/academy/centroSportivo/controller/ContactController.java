package it.euris.academy.centroSportivo.controller;

import it.euris.academy.centroSportivo.data.dto.ContactDto;
import it.euris.academy.centroSportivo.repository.projection.ContactCountProjection;
import it.euris.academy.centroSportivo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/v1")
    public Set<ContactDto> getAll() {
        return contactService.getAll();
    }

    @GetMapping("/v1/deleted")
    public Set<ContactDto> getAllDeleted() {
        return contactService.getAllDeleted();
    }

    @GetMapping("/v1/all")
    public Set<ContactDto> getReallyAll() {
        return contactService.getReallyAll();
    }

    @GetMapping("/v1/count")
    public ContactCountProjection getCount(){
        return contactService.getCount();
    }

    @GetMapping("/v1/{id}")
    public ContactDto getById(@PathVariable("id") Long id) {
        return contactService.get(id);
    }

    @PostMapping("/v1")
    public ContactDto add(@RequestBody ContactDto contactDto) {
        return contactService.add(contactDto);
    }

    @PutMapping("/v1")
    public ContactDto update(@RequestBody ContactDto contactDto) {
        return contactService.update(contactDto);
    }

    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return contactService.delete(id);
    }
}
