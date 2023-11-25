package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.ValidationException;
import com.apera.aperaserver.model.Company;
import com.apera.aperaserver.model.Person;
import com.apera.aperaserver.model.User;
import com.apera.aperaserver.repository.CompanyRepository;
import com.apera.aperaserver.repository.PersonRepository;
import com.apera.aperaserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public Person createPerson(Person entity) {
    User user = new User();
    user.setEmail(entity.getUser().getEmail());
    user.setPassword(entity.getUser().getPassword());
    user.setUsername(entity.getUser().getUsername());

    user = saveUser(user);

    Person person = new Person();
    person.setUser(user);
    person.setName(entity.getName());
    person.setPhoneNumber(entity.getPhoneNumber());
    person.setCpf(entity.getCpf());
    person.setGender(entity.getGender());
    person.setBirthDate(entity.getBirthDate());
    person.setAddress(entity.getAddress());

    person = savePerson(person);

    return person;
    }

    public Company createCompany(Company entity) {
        User user = new User();
        user.setEmail(entity.getUser().getEmail());
        user.setPassword(entity.getUser().getPassword());
        user.setUsername(entity.getUser().getUsername());

        user = saveUser(user);

        Company company = new Company();
        company.setUser(user);
        company.setFantasyName(entity.getFantasyName());
        company.setPhoneNumber(entity.getPhoneNumber());
        company.setCnpj(entity.getCnpj());
        company.setCorporateReason(entity.getCorporateReason());
        company.setAddress(entity.getAddress());

        company = saveCompany(company);

        return company;
    }

    @Transactional
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Transactional
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Page<Person> buscarTodasPessoas(String filter, Pageable pageable) {
        return personRepository.findAll(filter, Person.class, pageable);
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public Person updatePerson(Long id, Person updatedPerson) {
        Optional<Person> existingPersonOptional = personRepository.findById(id);

        if (existingPersonOptional.isPresent()) {
            Person existingPerson = existingPersonOptional.get();
            existingPerson.setName(updatedPerson.getName());
            existingPerson.setPhoneNumber(updatedPerson.getPhoneNumber());
            existingPerson.setCpf(updatedPerson.getCpf());
            existingPerson.setGender(updatedPerson.getGender());
            existingPerson.setBirthDate(updatedPerson.getBirthDate());
            existingPerson.setAddress(updatedPerson.getAddress());

            return personRepository.save(existingPerson);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

        @Transactional
        public Company updateCompany(Long id, Company updatedCompany) {
            Optional<Company> existingCompanyOptional = companyRepository.findById(id);

            if (existingCompanyOptional.isPresent()) {
                Company existingCompany = existingCompanyOptional.get();
                existingCompany.setFantasyName(updatedCompany.getFantasyName());
                existingCompany.setPhoneNumber(updatedCompany.getPhoneNumber());
                existingCompany.setCnpj(updatedCompany.getCnpj());
                existingCompany.setCorporateReason(updatedCompany.getCorporateReason());
                existingCompany.setAddress(updatedCompany.getAddress());

                return companyRepository.save(existingCompany);
            } else {
                return null;
            }
        }

        @Transactional
        public boolean deleteCompany(Long id) {
            if (companyRepository.existsById(id)) {
                companyRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }


    }