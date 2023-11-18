package com.apera.aperaserver.model;
import javax.persistence.*;

@Embeddable
public class Asset  {
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "corporate_reason", length = 100, nullable = false)
    private String corporateReason;
    @Column(name = "company_image")
    private String companyImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorporateReason() {
        return corporateReason;
    }

    public void setCorporateReason(String corporateReason) {
        this.corporateReason = corporateReason;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }
}
