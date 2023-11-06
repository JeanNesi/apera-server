package com.apera.aperaserver.resource;

import com.apera.aperaserver.enterprise.QuantidadeVendaException;
import com.apera.aperaserver.model.Venda;
import com.apera.aperaserver.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Venda entity) throws QuantidadeVendaException {
        Venda save = vendaService.salvarVenda(entity);
        return ResponseEntity.created(URI.create("/api/vendas" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {

        Page<Venda> vendas = vendaService.buscarTodos(filter, PageRequest.of(page, size));
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Venda venda = vendaService.buscarPorId(id);
        return ResponseEntity.ok(venda);
    }
}
