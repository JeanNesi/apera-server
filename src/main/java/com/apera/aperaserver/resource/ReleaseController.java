package com.apera.aperaserver.resource;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.enterprise.ResourceNotFoundException;
import com.apera.aperaserver.model.Release;
import com.apera.aperaserver.resource.representation.ReleaseDTO;
import com.apera.aperaserver.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/release")
public class ReleaseController extends AbstractController {
    @Autowired
    private ReleaseService releaseService;

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = true) Long walletId) {
        List<Release> releases = releaseService.findAllReleasesByWallet(walletId);
        List<ReleaseDTO> releaseDTOS = ReleaseDTO.fromEntity(releases);
        return ResponseEntity.ok(releaseDTOS);
    }

    @GetMapping("/wallet")
    public ResponseEntity wallet(@RequestParam(required = true) Long walletId) {
        return ResponseEntity.ok(releaseService.groupReleasesByAsset(walletId));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Release entity) {
        try {
            entity.checkRequiredFields();
            Release save = releaseService.createRelease(entity);
            return ResponseEntity.created(URI.create("/api/release" + entity.getId())).body(save);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return ResponseEntity.badRequest().body(resourceNotFoundException.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Release entity) {
        try {
            Release updatedRelase = releaseService.updateRelease(id, entity);
            return ResponseEntity.ok().body(updatedRelase);
        } catch (NotFoundException nfe) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        releaseService.deleteRelease(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping
//    public ResponseEntity findAll(@RequestParam(required = false) String filter,
//                                  @RequestParam(defaultValue = "0") int page,
//                                  @RequestParam(defaultValue = "10") int size) {
//
//        Page<Release> lancamentos = releaseService.buscarTodos(filter, PageRequest.of(page, size));
//        Page<ReleaseDTO> lancamentosDTOS = ReleaseDTO.fromEntity(lancamentos);
//        return ResponseEntity.ok(lancamentosDTOS);
//    }


//    @GetMapping("{id}")
//    public ResponseEntity findById(@PathVariable("id") Long id) {
//        Release release = releaseService.buscarPorId(id);
//        return ResponseEntity.ok(release);
//    }


}
