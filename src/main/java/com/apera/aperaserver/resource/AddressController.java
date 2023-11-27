package com.apera.aperaserver.resource;

import com.apera.aperaserver.model.Address;
import com.apera.aperaserver.resource.representation.AddressDTO;
import com.apera.aperaserver.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PutMapping("/person/{id}")
    public ResponseEntity updateByPerson(@PathVariable("id") Long id, @RequestBody Address updatedAddress) {
        Address updated = addressService.updateByPerson(id, updatedAddress);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/company/{id}")
    public ResponseEntity updateByCompany(@PathVariable("id") Long id, @RequestBody Address updatedAddress) {
        Address updated = addressService.updateByCompany(id, updatedAddress);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity findAllAddress(@RequestParam(defaultValue = "") String filter,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        Page<Address> address = addressService.findAllAddress(filter, PageRequest.of(page, size));
        Page<AddressDTO> addressDTOS = AddressDTO.fromEntity(address);
        return ResponseEntity.ok(addressDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity findAddressById(@PathVariable("id") Long id) {
        Optional<Address> addressById = addressService.findAddressById(id);
        return ResponseEntity.ok(addressById);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

}
