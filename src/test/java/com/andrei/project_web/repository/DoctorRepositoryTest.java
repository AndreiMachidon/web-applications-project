package com.andrei.project_web.repository;

import com.andrei.project_web.domain.Doctor;
import com.andrei.project_web.repositories.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository repository;

    private Doctor testEntity;

    @BeforeEach
    void setup() {
        testEntity = new Doctor();
        testEntity.setName("Test Name");
        testEntity.setEmail("test@example.com");
        testEntity.setSpecialization("N/A");
        testEntity.setLicenseNumber("N/A");
        repository.save(testEntity);
    }

    @Test
    void testFindAll() {
        List<Doctor> list = repository.findAll();
        assertFalse(list.isEmpty(), "Lista nu trebuie să fie goală");
    }

    @Test
    void testFindById() {
        Optional<Doctor> found = repository.findById(testEntity.getId());
        assertTrue(found.isPresent(), "Entitatea trebuie găsită după ID");
    }

    @Test
    void testSave() {
        Doctor newEntity = new Doctor();
        testEntity.setName("Test Name");
        testEntity.setEmail("test@example.com");
        testEntity.setSpecialization("N/A");
        testEntity.setLicenseNumber("N/A");
        Doctor saved = repository.save(newEntity);
        assertNotNull(saved.getId(), "Entitatea salvată trebuie să aibă ID");
    }

    @Test
    void testDeleteById() {
        repository.deleteById(testEntity.getId());
        assertTrue(repository.findById(testEntity.getId()).isEmpty(), "Entitatea trebuie să fie ștearsă");
    }
}
