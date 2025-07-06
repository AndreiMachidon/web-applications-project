package com.andrei.project_web.repository;

import com.andrei.project_web.domain.Patient;
import com.andrei.project_web.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class PatientRepositoryTest {
    @Autowired
    private PatientRepository repository;

    private Patient testEntity;

    @BeforeEach
    void setup() {
        testEntity = new Patient();
        testEntity.setName("Test Name");
        testEntity.setEmail("test@example.com");
        testEntity.setBirthDate(LocalDate.now().minusYears(25));
        testEntity.setPhone("1234567890");
        testEntity.setAddress("Test Address");
        repository.save(testEntity);
    }

    @Test
    void testFindAll() {
        List<Patient> list = repository.findAll();
        assertFalse(list.isEmpty(), "Lista nu trebuie să fie goală");
    }

    @Test
    void testFindById() {
        Optional<Patient> found = repository.findById(testEntity.getId());
        assertTrue(found.isPresent(), "Entitatea trebuie găsită după ID");
    }

    @Test
    void testSave() {
        Patient newEntity = new Patient();
        testEntity.setName("Test Name");
        testEntity.setEmail("test@example.com");
        testEntity.setBirthDate(LocalDate.now().minusYears(10));
        testEntity.setPhone("1234567890");
        testEntity.setAddress("Test Address");
        Patient saved = repository.save(newEntity);
        assertNotNull(saved.getId(), "Entitatea salvată trebuie să aibă ID");
    }

    @Test
    void testDeleteById() {
        repository.deleteById(testEntity.getId());
        assertTrue(repository.findById(testEntity.getId()).isEmpty(), "Entitatea trebuie să fie ștearsă");
    }
}
