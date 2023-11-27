package com.apera.aperaserver.resource;

import com.apera.aperaserver.model.UserFavoriteAssets;
import com.apera.aperaserver.resource.representation.FavoriteAssetsDTO;
import com.apera.aperaserver.service.FavoriteAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/favoriteAssets")
public class FavoriteAssetsController {
    @Autowired
    private @ReadOnlyProperty FavoriteAssetsService favoriteAssetsService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid UserFavoriteAssets entity) {
        UserFavoriteAssets save = favoriteAssetsService.salvarAtivosFavoritos(entity);
        return ResponseEntity.created(URI.create("/api/favoriteAssets" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(defaultValue = "") String filter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        var ativosFavoritos = favoriteAssetsService.buscarTodos(filter, PageRequest.of(page, size));
        var favoriteAssetsDTOS = FavoriteAssetsDTO.fromEntity(ativosFavoritos);

        return ResponseEntity.ok(favoriteAssetsDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        var userFavoriteAssets = favoriteAssetsService.buscarPorId(id);

        return ResponseEntity.ok(userFavoriteAssets);
    }

    @GetMapping("/user")
    public ResponseEntity findAllByUser(@RequestParam(required = true) Long userId) {
        return ResponseEntity.ok(favoriteAssetsService.findAllByUser(userId));
    }

    @GetMapping("/name/{name}/{userId}")
    public ResponseEntity findByName(@PathVariable("name") String name, @PathVariable("userId") Long userId) {
        var userFavoriteAssets = favoriteAssetsService.buscarPorNome(name, userId);
        return ResponseEntity.ok(userFavoriteAssets);
    }


    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        favoriteAssetsService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
