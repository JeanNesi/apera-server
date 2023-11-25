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

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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

    public Page<Person> buscarTodos(String filter, Pageable pageable) {
        return personRepository.findAll(filter, Person.class, pageable);
    }

}
