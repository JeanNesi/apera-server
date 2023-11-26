package com.apera.aperaserver.resource.representation;

import com.apera.aperaserver.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class AddressDTO {
    private Long id;
    private String cep;
    private String city;
    private String complement;
    private String neighborhood;
    private Integer number;
    private String street;
    private String uf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public static AddressDTO fromEntity(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCep(address.getCep());
        addressDTO.setCity(address.getCity());
        addressDTO.setComplement(address.getComplement());
        addressDTO.setNeighborhood(address.getNeighborhood());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setUf(address.getUf());
        return addressDTO;
    }

    public Address toEntity() {
        Address address = new Address();
        address.setCep(this.getCep());
        address.setCity(this.getCity());
        address.setComplement(this.getComplement());
        address.setNeighborhood(this.getNeighborhood());
        address.setNumber(this.getNumber());
        address.setStreet(this.getStreet());
        address.setUf(this.getUf());
        return address;
    }

    public List<AddressDTO> fromEntity(List<Address> address) {
        return address.stream().map(adrss -> fromEntity(adrss)).collect(Collectors.toList());
    }

    public static Page<AddressDTO> fromEntity(Page<Address> address) {
        List<AddressDTO> addressFind = address.stream().map(adrss -> fromEntity(adrss)).collect(Collectors.toList());
        Page<AddressDTO> addressDTO = new PageImpl<>(addressFind, address.getPageable(), address.getTotalElements());
        return addressDTO;
    }
}
