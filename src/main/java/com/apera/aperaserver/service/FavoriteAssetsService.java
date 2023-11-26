package com.apera.aperaserver.service;

import com.apera.aperaserver.model.UserFavoriteAssets;
import com.apera.aperaserver.repository.FavoriteAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FavoriteAssetsService {
    @Autowired
    private @ReadOnlyProperty FavoriteAssetsRepository favoriteAssetsRepository;

    @Transactional
    public UserFavoriteAssets salvarAtivosFavoritos(UserFavoriteAssets entity) {
        return favoriteAssetsRepository.save(entity);
    }

    @Transactional
    public Page<UserFavoriteAssets> buscarTodos(String filter, Pageable pageable) {
        return favoriteAssetsRepository.findAll(filter.replace(" ", "+"), UserFavoriteAssets.class, pageable);
    }

    @Transactional
    public UserFavoriteAssets buscarPorId(Long id) {
        return favoriteAssetsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deletar(Long id) {
        favoriteAssetsRepository.deleteById(id);
    }
}
