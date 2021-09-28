package it.euris.academy.centroSportivo.service.impl;

import it.euris.academy.centroSportivo.data.dto.AddressDto;
import it.euris.academy.centroSportivo.data.model.Address;
import it.euris.academy.centroSportivo.exception.IdMustBeNullException;
import it.euris.academy.centroSportivo.exception.IdMustNotBeNullException;
import it.euris.academy.centroSportivo.repository.AddressRepository;
import it.euris.academy.centroSportivo.repository.projection.AddressCountProjection;
import it.euris.academy.centroSportivo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Set<AddressDto> getAll() {
        return addressRepository.findAll().stream()
                .map(Address::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public AddressDto get(Long id) {
        Optional<Address> gotAddress = addressRepository.findById(id);
        return gotAddress.map(Address::toDto).orElse(null);
    }

    @Override
    public AddressDto add(AddressDto dataToInsert) {
        if(dataToInsert.getId() != null)
            throw new IdMustBeNullException();
        return addressRepository.save(dataToInsert.toModel()).toDto();
    }

    @Override
    public AddressDto update(AddressDto dataToInsert) {
        if(dataToInsert.getId() == null)
            throw new IdMustNotBeNullException();
        return addressRepository.save(dataToInsert.toModel()).toDto();
    }

    @Override
    public Boolean delete(Long id) {
        addressRepository.logicalDelete(id);
        return addressRepository.findById(id).get().getDeleted();
    }

    @Override
    public AddressCountProjection getCount(){
        return addressRepository.getCount();
    }

    @Override
    public Set<AddressDto> getReallyAll() {
        return addressRepository.getReallyAll().stream().map(Address::toDto).collect(Collectors.toSet());
    }

    @Override
    public Set<AddressDto> getAllDeleted() {
        return addressRepository.getAllDeleted().stream().map(Address::toDto).collect(Collectors.toSet());
    }
}
