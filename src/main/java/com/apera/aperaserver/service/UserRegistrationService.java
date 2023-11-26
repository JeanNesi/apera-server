package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.*;
import com.apera.aperaserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    PasswordEncoder encoder;

    public Person createPerson(Person entity) {
        User user = new User();
        user.setEmail(entity.getUser().getEmail());
        user.setPassword(entity.getUser().getPassword());
        user.setUsername(entity.getUser().getUsername());

        user = saveUser(user);

        Address address = new Address();
        address = saveAddress(address);

        Wallet wallet = new Wallet();
        wallet.setName("Carteira 1");
        wallet.setUser(user);

        Person person = new Person();
        person.setUser(user);
        person.setName(entity.getName());
        person.setPhoneNumber(entity.getPhoneNumber());
        person.setCpf(entity.getCpf());
        person.setGender(entity.getGender());
        person.setBirthDate(entity.getBirthDate());
        person.setAddress(address);

        person = savePerson(person);

        return person;
    }

    public Company createCompany(Company entity) {
        User user = new User();
        user.setEmail(entity.getUser().getEmail());
        user.setPassword(encoder.encode(entity.getUser().getPassword()));
        user.setUsername(entity.getUser().getUsername());

        user = saveUser(user);

        Address address = new Address();

        address = saveAddress(address);

        Wallet wallet = new Wallet();
        wallet.setName("Carteira 1");
        wallet.setUser(user);

        saveWallet(wallet);

        Company company = new Company();
        company.setUser(user);
        company.setFantasyName(entity.getFantasyName());
        company.setPhoneNumber(entity.getPhoneNumber());
        company.setCnpj(entity.getCnpj());
        company.setCorporateReason(entity.getCorporateReason());
        company.setAddress(address);

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

    @Transactional
    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Transactional
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
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
            throw new NotFoundException("Não foi possível atualizar os dados da pessoa!");
        }
    }

    @Transactional
    public boolean deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException("Pessoa não encontrada!");
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
            throw new NotFoundException("Não foi possível atualizar os dados da empresa!");
        }
    }

    @Transactional
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException("Empresa não encontrada!");
        }
    }

    @Transactional
    public Optional<Company> findCompanyById(Long userId) {
        return companyRepository.findById(QCompany.company.user.id.eq(userId));
    }

    @Transactional
    public Page<Company> buscarTodasEmpresas(String filter, Pageable pageable) {
        return companyRepository.findAll(filter, Company.class, pageable);
    }

}
