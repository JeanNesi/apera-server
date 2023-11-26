package com.apera.aperaserver.resource;

import com.apera.aperaserver.model.Company;
import com.apera.aperaserver.model.Person;
import com.apera.aperaserver.repository.UserRepository;
import com.apera.aperaserver.resource.representation.CompanyDTO;
import com.apera.aperaserver.resource.representation.PersonDTO;
import com.apera.aperaserver.resource.security.payload.response.MessageResponse;
import com.apera.aperaserver.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/userRegistration")
public class UserRegistrationController extends AbstractController {
    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/person")
    public ResponseEntity create(@RequestBody @Valid Person entity) {
        if (userRepository.existsByUsername(entity.getUser().getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(entity.getUser().getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Person save = userRegistrationService.createPerson(entity);
        return ResponseEntity.created(URI.create("api/userRegistration" + entity.getId())).body(save);
    }

    @PostMapping("/company")
    public ResponseEntity create(@RequestBody @Valid Company entity) {
        if (userRepository.existsByUsername(entity.getUser().getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(entity.getUser().getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Company save = userRegistrationService.createCompany(entity);
        return ResponseEntity.created(URI.create(("api/userRegistration" + entity.getId()))).body(save.getUser().getId());
    }

    @GetMapping("/person")
    public ResponseEntity findAllPerson(@RequestParam(required = false) String filter,
                                        @RequestParam(required = false) int page,
                                        @RequestParam(required = false) int size) {
        Page<Person> person = userRegistrationService.buscarTodasPessoas(filter, PageRequest.of(page, size));
        Page<PersonDTO> personDTOS = PersonDTO.fromEntity(person);
        return ResponseEntity.ok(personDTOS);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity getPersonById(@PathVariable Long id) {
        Optional<Person> personOptional = userRegistrationService.getPersonById(id);

        if (personOptional.isPresent()) {
            PersonDTO personDTO = PersonDTO.fromEntity(personOptional.get());
            return ResponseEntity.ok(personDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatePerson/{id}")
    public ResponseEntity updatePerson(@PathVariable("id") Long id, @RequestBody Person updatedPerson) {
        Person updated = userRegistrationService.updatePerson(id, updatedPerson);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id) {
        boolean deleted = userRegistrationService.deletePerson(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/company")
    public ResponseEntity findAllCompany(@RequestParam(required = false) String filter,
                                         @RequestParam(required = false) int page,
                                         @RequestParam(required = false) int size) {
        Page<Company> company = userRegistrationService.buscarTodasEmpresas(filter, PageRequest.of(page, size));
        Page<CompanyDTO> companyDTOS = CompanyDTO.fromEntity(company);
        return ResponseEntity.ok(companyDTOS);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity findCompanyByUserId(@RequestParam(required = false) Long userId) {
        Optional<Company> companyOptional = userRegistrationService.findCompanyById(userId);

        if (companyOptional.isPresent()) {
            CompanyDTO companyDTO = CompanyDTO.fromEntity(companyOptional.get());
            return ResponseEntity.ok(companyDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editCompany/{id}")
    public ResponseEntity updateCompany(@PathVariable("id") Long id, @RequestBody Company updatedCompany) {
        Company updated = userRegistrationService.updateCompany(id, updatedCompany);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteCompany/{id}")
    public ResponseEntity deleteCompany(@PathVariable Long id) {
        boolean deleted = userRegistrationService.deleteCompany(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
