package com.apera.aperaserver.resource;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.enterprise.ResourceNotFoundException;
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
@RequestMapping("/api/wallet")
public class WalletController extends AbstractController {
    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String filter,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Page<Wallet> carteiras = walletService.buscarTodos(filter, PageRequest.of(page, size));
        Page<WalletDTO> carteiraDTO = WalletDTO.fromEntity(carteiras);
        return ResponseEntity.ok(carteiraDTO);
    }


    @GetMapping("/user")
    public ResponseEntity user(@RequestParam(required = true) Long userId) {
        List<Wallet> wallets = walletService.findAllWalletByUser(userId);
        List<WalletDTO> walletDTOS = WalletDTO.fromEntity(wallets);
        return ResponseEntity.ok(walletDTOS);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Wallet entity) {
        try {
            entity.checkRequiredFields();
            Wallet save = walletService.createWallet(entity);
            return ResponseEntity.created(URI.create("/api/wallet" + entity.getId())).body(save);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return ResponseEntity.badRequest().body(resourceNotFoundException.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Wallet entity) {
        try {
            Wallet updatedWallet = walletService.updateWallet(id, entity);
            return ResponseEntity.ok().body(updatedWallet);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }

}
