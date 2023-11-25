package com.apera.aperaserver.resource;

import com.apera.aperaserver.model.Company;
import com.apera.aperaserver.model.Person;
import com.apera.aperaserver.resource.representation.PersonDTO;
import com.apera.aperaserver.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/userRegistration")
public class UserRegistrationController extends AbstractController {
    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping("/person")
    public ResponseEntity create(@RequestBody @Valid Person entity) {
        Person save = userRegistrationService.createPerson(entity);
        return ResponseEntity.created(URI.create("api/userRegistration" + entity.getId())).body(save);
    }

    @PostMapping("/company")
    public ResponseEntity create(@RequestBody @Valid Company entity) {
        Company save = userRegistrationService.createCompany(entity);
        return ResponseEntity.created(URI.create(("api/userRegistration" + entity.getId()))).body(save);
    }

    @GetMapping("/person")
    public ResponseEntity findAll(@RequestParam(required = false) String filter,
                                  @RequestParam(required = false) int page,
                                  @RequestParam(required = false) int size) {
        Page<Person> person = userRegistrationService.buscarTodos(filter, PageRequest.of(page, size));
        Page<PersonDTO> personDTOS = PersonDTO.fromEntity(person);
        return ResponseEntity.ok(personDTOS);
    }
}
