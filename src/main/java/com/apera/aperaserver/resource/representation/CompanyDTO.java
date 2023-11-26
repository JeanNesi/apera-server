package com.apera.aperaserver.resource.representation;

import com.apera.aperaserver.model.Address;
import com.apera.aperaserver.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyDTO {
    private Long id;
    private String fantasyName;
    private String corporateReason;
    private String cnpj;
    private String phoneNumber;
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getCorporateReason() {
        return corporateReason;
    }

    public void setCorporateReason(String corporateReason) {
        this.corporateReason = corporateReason;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static CompanyDTO fromEntity(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setFantasyName(company.getFantasyName());
        companyDTO.setCorporateReason(company.getCorporateReason());
        companyDTO.setCnpj(company.getCnpj());
        companyDTO.setPhoneNumber(company.getPhoneNumber());
        companyDTO.setAddress(company.getAddress());
        return companyDTO;
    }

    public Company toEntity() {
        Company company = new Company();
        company.setId(this.getId());
        company.setFantasyName(this.getFantasyName());
        company.setCorporateReason(this.getCorporateReason());
        company.setCnpj(this.getCnpj());
        company.setPhoneNumber(this.getPhoneNumber());
        company.setAddress(this.getAddress());
        return company;
    }

    public List<CompanyDTO> fromEntity(List<Company> company) {
        return company.stream().map(comp -> fromEntity(comp)).collect(Collectors.toList());
    }

    public static Page<CompanyDTO> fromEntity(Page<Company> company) {
        List<CompanyDTO> companyFind = company.stream().map(comp -> fromEntity(comp)).collect(Collectors.toList());
        Page<CompanyDTO> companyDTO = new PageImpl<>(companyFind, company.getPageable(), company.getTotalElements());
        return companyDTO;
    }
}
