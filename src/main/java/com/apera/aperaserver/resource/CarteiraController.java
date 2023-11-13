package com.apera.aperaserver.resource;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.Carteira;
import com.apera.aperaserver.service.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/carteira")
public class CarteiraController extends AbstractController {
    @Autowired
    private CarteiraService carteiraService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Carteira entity) {
        Carteira save = carteiraService.salvarCarteira(entity);
        return ResponseEntity.created(URI.create("/api/carteira" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<Carteira> carteiras = carteiraService.buscarTodos();
        return ResponseEntity.ok(carteiras);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Carteira carteira = carteiraService.buscarPorId(id);
        return ResponseEntity.ok(carteira);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Carteira entity) {
        try {
            Carteira alterada = carteiraService.alterar(id, entity);
            return ResponseEntity.ok().body(alterada);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        carteiraService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
