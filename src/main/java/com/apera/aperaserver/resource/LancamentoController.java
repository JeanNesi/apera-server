package com.apera.aperaserver.resource;

import com.apera.aperaserver.model.Lancamento;
import com.apera.aperaserver.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {
    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Lancamento entity) {
        Lancamento save = lancamentoService.salvarLancamento(entity);
        return ResponseEntity.created(URI.create("/api/lancamento" + entity.getId())).body(save);
    }

}
