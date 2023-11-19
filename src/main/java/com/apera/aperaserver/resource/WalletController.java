package com.apera.aperaserver.resource;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.Wallet;
import com.apera.aperaserver.resource.representation.WalletDTO;
import com.apera.aperaserver.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/carteira")
public class WalletController extends AbstractController {
    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Wallet entity) {
        Wallet save = walletService.salvarCarteira(entity);
        return ResponseEntity.created(URI.create("/api/carteira" + entity.getId())).body(save);
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam (required = false) String filter,
                                  @RequestParam (defaultValue = "0") int page,
                                  @RequestParam (defaultValue = "10") int size) {
        Page<Wallet> carteiras = walletService.buscarTodos(filter, PageRequest.of(page, size));
        Page<WalletDTO> carteiraDTO = WalletDTO.fromEntity(carteiras);
        return ResponseEntity.ok(carteiraDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Wallet carteira = walletService.buscarPorId(id);
        return ResponseEntity.ok(carteira);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Wallet entity) {
        try {
            Wallet alterada = walletService.alterar(id, entity);
            return ResponseEntity.ok().body(alterada);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }



    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        walletService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
