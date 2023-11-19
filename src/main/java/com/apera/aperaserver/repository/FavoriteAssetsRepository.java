package com.apera.aperaserver.repository;

import com.apera.aperaserver.enterprise.CustomQuerydslPredicateExecutor;
import com.apera.aperaserver.model.Asset;
import com.apera.aperaserver.model.UserFavoriteAssets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteAssetsRepository extends JpaRepository<UserFavoriteAssets, Long>, CustomQuerydslPredicateExecutor<UserFavoriteAssets> {
}
