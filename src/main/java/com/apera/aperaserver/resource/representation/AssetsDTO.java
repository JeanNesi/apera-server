package com.apera.aperaserver.resource.representation;

import com.apera.aperaserver.model.Asset;

public class AssetsDTO {
    private String name;
    private String corporateReason;
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

    public static Asset toEntity(AssetsDTO assetsDTO) {
        Asset asset = new Asset();
        asset.setName(assetsDTO.getName());
        asset.setCorporateReason(assetsDTO.getCorporateReason());
        asset.setCompanyImage(assetsDTO.getCompanyImage());
        return asset;
    }

    public static AssetsDTO fromEntity(Asset asset) {
        AssetsDTO assetsDTO = new AssetsDTO();
        assetsDTO.setName(asset.getName());
        assetsDTO.setCorporateReason(asset.getCorporateReason());
        assetsDTO.setCompanyImage(asset.getCompanyImage());
        return assetsDTO;
    }


}
