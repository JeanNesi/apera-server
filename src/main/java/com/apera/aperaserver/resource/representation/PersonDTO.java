package com.apera.aperaserver.resource.representation;

import com.apera.aperaserver.model.Address;
import com.apera.aperaserver.model.EGender;
import com.apera.aperaserver.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDTO {
    private Long id;
    private EGender gender;
    private Date birthDate;
    private String cpf;
    private String name;
    private Address adress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public static PersonDTO fromEntity(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setCpf(person.getCpf());
        personDTO.setGender(person.getGender());
        personDTO.setBirthDate(person.getBirthDate());
        personDTO.setAdress(person.getAddress());
        return  personDTO;
    }

    public Person toEntity() {
        Person person = new Person();
        person.setId(this.getId());
        person.setName(this.getName());
        person.setCpf(this.getCpf());
        person.setGender(this.getGender());
        person.setBirthDate(this.getBirthDate());
        person.setAddress(this.getAdress());
        return person;
    }

    public static List<PersonDTO> fromEntity(List<Person> person) {
        return person.stream().map(per -> fromEntity(per)).collect(Collectors.toList());
    }

    public static Page<PersonDTO> fromEntity(Page<Person> person) {
        List<PersonDTO> personFind = person.stream().map(per -> fromEntity(per)).collect(Collectors.toList());
        Page<PersonDTO> personDTO = new PageImpl<>(personFind, person.getPageable(), person.getTotalElements());
        return personDTO;
    }
}
