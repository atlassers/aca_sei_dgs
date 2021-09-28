package it.euris.academy.centroSportivo.service.impl;

import it.euris.academy.centroSportivo.data.dto.ContactDto;
import it.euris.academy.centroSportivo.data.dto.ContactDto;
import it.euris.academy.centroSportivo.data.dto.ContactDto;
import it.euris.academy.centroSportivo.data.model.Contact;
import it.euris.academy.centroSportivo.data.model.Contact;
import it.euris.academy.centroSportivo.data.model.Contact;
import it.euris.academy.centroSportivo.exception.IdMustBeNullException;
import it.euris.academy.centroSportivo.exception.IdMustNotBeNullException;
import it.euris.academy.centroSportivo.repository.ContactRepository;
import it.euris.academy.centroSportivo.repository.projection.ContactCountProjection;
import it.euris.academy.centroSportivo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Set<ContactDto> getAll() {
        return contactRepository.findAll().stream().map(Contact::toDto).collect(Collectors.toSet());
    }

    @Override
    public ContactDto get(Long id) {
        Optional<Contact> gotContact = contactRepository.findById(id);
        return gotContact.map(Contact::toDto).orElse(null);
    }

    @Override
    public ContactDto add(ContactDto dataToInsert) {
        if(dataToInsert.getId() != null)
            throw new IdMustBeNullException();
        return contactRepository.save(dataToInsert.toModel()).toDto();
    }

    @Override
    public ContactDto update(ContactDto dataToInsert) {
        if(dataToInsert.getId() == null)
            throw new IdMustNotBeNullException();
        return contactRepository.save(dataToInsert.toModel()).toDto();
    }

    @Override
    public Boolean delete(Long id) {
        contactRepository.logicalDelete(id);
        return contactRepository.findById(id).get().getDeleted();
    }

    @Override
    public ContactCountProjection getCount(){
        return contactRepository.getCount();
    }

    @Override
    public Set<ContactDto> getReallyAll() {
        return contactRepository.getReallyAll().stream().map(Contact::toDto).collect(Collectors.toSet());
    }

    @Override
    public Set<ContactDto> getAllDeleted() {
        return contactRepository.getAllDeleted().stream().map(Contact::toDto).collect(Collectors.toSet());
    }
}
