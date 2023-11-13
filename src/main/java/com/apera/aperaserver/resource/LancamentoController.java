package com.apera.aperaserver.resource;

import com.apera.aperaserver.model.Lancamento;
import com.apera.aperaserver.resource.representation.LancamentoDTO;
import com.apera.aperaserver.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController extends AbstractController {
    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Lancamento entity) {
        Lancamento save = lancamentoService.salvarLancamento(entity);
        return ResponseEntity.created(URI.create("/api/lancamento" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {

        Page<Lancamento> lancamentos = lancamentoService.buscarTodos(filter, PageRequest.of(page, size));
        Page<LancamentoDTO> lancamentosDTOS = LancamentoDTO.fromEntity(lancamentos);
        return ResponseEntity.ok(lancamentosDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Lancamento lancamento = lancamentoService.buscarPorId(id);
        return ResponseEntity.ok(lancamento);
    }

}
