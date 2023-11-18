package com.apera.aperaserver.model;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company extends Profile {
    @Column(name = "fantasy_name", nullable = false, length = 100)
    private String fantasyName;

    @Column(name = "corporate_reason", length = 100)
    private String corporateReason;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

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
}
