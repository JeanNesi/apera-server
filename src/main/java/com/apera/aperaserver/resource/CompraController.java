package com.apera.aperaserver.resource;

import com.apera.aperaserver.model.Compra;
import com.apera.aperaserver.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController extends AbstractController {
    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Compra entity) {
        Compra save = compraService.salvarCompra(entity);
        return ResponseEntity.created(URI.create("/api/compras" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll() {
        List<Compra> compras = compraService.buscarTodos();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("{id")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Compra compra = compraService.buscarPorId(id);
        return ResponseEntity.ok(compra);
    }

}
