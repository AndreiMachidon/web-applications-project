package com.andrei.project_web.repository;

import com.andrei.project_web.domain.Clinic;
import com.andrei.project_web.repositories.ClinicRepository;
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
public class ClinicRepositoryTest {
    @Autowired
    private ClinicRepository repository;

    private Clinic testEntity;

    @BeforeEach
    void setup() {
        testEntity = new Clinic();
        testEntity.setName("Test Name");
        testEntity.setLocation("N/A");
        testEntity.setPhoneNumber("1234567890");
        repository.save(testEntity);
    }

    @Test
    void testFindAll() {
        List<Clinic> list = repository.findAll();
        assertFalse(list.isEmpty(), "Lista nu trebuie să fie goală");
    }

    @Test
    void testFindById() {
        Optional<Clinic> found = repository.findById(testEntity.getId());
        assertTrue(found.isPresent(), "Entitatea trebuie găsită după ID");
    }

    @Test
    void testSave() {
        Clinic newEntity = new Clinic();
        testEntity.setName("Test Name");
        testEntity.setLocation("N/A");
        testEntity.setPhoneNumber("1234567890");
        Clinic saved = repository.save(newEntity);
        assertNotNull(saved.getId(), "Entitatea salvată trebuie să aibă ID");
    }

    @Test
    void testDeleteById() {
        repository.deleteById(testEntity.getId());
        assertTrue(repository.findById(testEntity.getId()).isEmpty(), "Entitatea trebuie să fie ștearsă");
    }
}
