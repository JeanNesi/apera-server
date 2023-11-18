package com.apera.aperaserver;

import com.apera.aperaserver.model.EGender;
import com.apera.aperaserver.model.Gender;
import com.apera.aperaserver.repository.GenderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private GenderRepository genderRepository;

    public DatabaseInitializer(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public void run(String... args) {
        for (EGender eGender : EGender.values()) {
            if(!genderRepository.existsByName(eGender)) {
                Gender gender = new Gender();
                gender.setName(eGender);
                genderRepository.save(gender);
            }
        }
    }
}