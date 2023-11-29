package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.QUser;
import com.apera.aperaserver.model.QUserFavoriteAssets;
import com.apera.aperaserver.model.UserFavoriteAssets;
import com.apera.aperaserver.repository.FavoriteAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FavoriteAssetsService {
    @Autowired
    private @ReadOnlyProperty FavoriteAssetsRepository favoriteAssetsRepository;

    @Transactional
    public UserFavoriteAssets salvarAtivosFavoritos(UserFavoriteAssets entity) {
      boolean assetCount = favoriteAssetsRepository.exists(QUserFavoriteAssets.userFavoriteAssets.asset.name.eq(entity.getAsset().getName()).and(QUserFavoriteAssets.userFavoriteAssets.user.id.eq(entity.getUser().getId())));

      if(assetCount) {
          throw new NotFoundException("Você já possui este ativo na lista de favoritos!");
      }
        return favoriteAssetsRepository.save(entity);
    }

    @Transactional
    public Page<UserFavoriteAssets> buscarTodos(String filter, Pageable pageable) {
        return favoriteAssetsRepository.findAll(filter.replace(" ", "+"), UserFavoriteAssets.class, pageable);
    }

    @Transactional
    public List<UserFavoriteAssets> findAllByUser(Long userId) {
        return (List<UserFavoriteAssets>) favoriteAssetsRepository.findAll(QUser.user.id.eq(userId));
    }

    @Transactional
    public UserFavoriteAssets buscarPorId(Long id) {
        return favoriteAssetsRepository.findById(id).orElse(null);
    }

    @Transactional
    public List buscarPorNome(String name, Long userId) {
        return favoriteAssetsRepository.findAll(QUserFavoriteAssets.userFavoriteAssets.asset.name.eq(name).and(QUserFavoriteAssets.userFavoriteAssets.user.id.eq(userId)));
    }

    @Transactional
    public void deletar(Long id) {
        favoriteAssetsRepository.deleteById(id);
    }
}
