package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.*;
import com.apera.aperaserver.repository.AddressRepository;
import com.apera.aperaserver.repository.CompanyRepository;
import com.apera.aperaserver.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import javax.persistence.EntityManager;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public Address updateByPerson(Long idPerson, Address updatedAddress) {
        Optional<Person> existingPersonOptional = personRepository.findById(idPerson);

        if (existingPersonOptional.isPresent()) {
            Address existingAddress = existingPersonOptional.get().getAddress();
            existingAddress.setCep(updatedAddress.getCep());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setComplement(updatedAddress.getComplement());
            existingAddress.setNeighborhood(updatedAddress.getNeighborhood());
            existingAddress.setNumber(updatedAddress.getNumber());
            existingAddress.setStreet(updatedAddress.getStreet());
            existingAddress.setUf(updatedAddress.getUf());

            return addressRepository.save(existingAddress);
        } else {
            throw new NotFoundException("Endereço não encontrado!");
        }
    }

    @Transactional
    public Address updateByCompany(Long idCompany, Address updatedAddress) {
        Optional<Company> existingCompanyOptional = companyRepository.findById(idCompany);

        if (existingCompanyOptional.isPresent()) {
            Address existingAddress = existingCompanyOptional.get().getAddress();
            existingAddress.setCep(updatedAddress.getCep());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setComplement(updatedAddress.getComplement());
            existingAddress.setNeighborhood(updatedAddress.getNeighborhood());
            existingAddress.setNumber(updatedAddress.getNumber());
            existingAddress.setStreet(updatedAddress.getStreet());
            existingAddress.setUf(updatedAddress.getUf());

            return addressRepository.save(existingAddress);
        } else {
            throw new NotFoundException("Endereço não encontrado!");
        }
    }

    @Transactional
    public Optional<Address> findAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Transactional
    public Page<Address> findAllAddress(String filter, Pageable pageable) {
        return addressRepository.findAll(filter.replace(" ", "+"), Address.class, pageable);
    }

    @Transactional
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
