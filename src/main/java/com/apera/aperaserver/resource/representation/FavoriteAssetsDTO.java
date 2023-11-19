package com.apera.aperaserver.resource.representation;

import com.apera.aperaserver.model.UserFavoriteAssets;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class FavoriteAssetsDTO {
    private Long id;
    private String name;
    private String corporateReason;
    private String companyImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public static FavoriteAssetsDTO fromEntity(UserFavoriteAssets userFavoriteAssets) {
        FavoriteAssetsDTO dto = new FavoriteAssetsDTO();
        dto.setId(userFavoriteAssets.getId());
        dto.setName(userFavoriteAssets.getAsset().getName());
        dto.setCorporateReason(userFavoriteAssets.getAsset().getCorporateReason());
        dto.setCompanyImage(userFavoriteAssets.getAsset().getCompanyImage());
        return dto;
    }
    public UserFavoriteAssets toEntity() {
        UserFavoriteAssets userFavoriteAssets = new UserFavoriteAssets();
        userFavoriteAssets.setId(this.getId());
        userFavoriteAssets.getAsset().setName(this.getName());
        userFavoriteAssets.getAsset().setCorporateReason(this.getCorporateReason());
        userFavoriteAssets.getAsset().setCompanyImage(this.getCompanyImage());
        return userFavoriteAssets;
    }
    public static List<FavoriteAssetsDTO> fromEntity(List<UserFavoriteAssets> userFavoriteAssets) {
        return userFavoriteAssets.stream().map(userFavoriteAsset -> fromEntity(userFavoriteAsset)).collect(Collectors.toList());
    }
    public static Page<FavoriteAssetsDTO> fromEntity(Page<UserFavoriteAssets> userFavoriteAssets) {
        List<FavoriteAssetsDTO> userFavoriteAssetsFind = userFavoriteAssets.stream().map(userFavoriteAsset -> fromEntity(userFavoriteAsset)).collect(Collectors.toList());
        Page<FavoriteAssetsDTO> favoriteAssetsDTO = new PageImpl<>(userFavoriteAssetsFind, userFavoriteAssets.getPageable(), userFavoriteAssets.getTotalElements());
        return favoriteAssetsDTO;
    }
}
