package com.andrei.project_web.repository;

import com.andrei.project_web.domain.Consultation;
import com.andrei.project_web.repositories.ConsultationRepository;
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
public class ConsultationRepositoryTest {
    @Autowired
    private ConsultationRepository repository;

    private Consultation testEntity;

    @BeforeEach
    void setup() {
        testEntity = new Consultation();
        testEntity.setSummary("Test Content");
        testEntity.setPrescription("Test Content");
        testEntity.setFollowUpNeeded(false);
        repository.save(testEntity);
    }

    @Test
    void testFindAll() {
        List<Consultation> list = repository.findAll();
        assertFalse(list.isEmpty(), "Lista nu trebuie să fie goală");
    }

    @Test
    void testFindById() {
        Optional<Consultation> found = repository.findById(testEntity.getId());
        assertTrue(found.isPresent(), "Entitatea trebuie găsită după ID");
    }

    @Test
    void testSave() {
        Consultation newEntity = new Consultation();
        testEntity.setSummary("Test Content");
        testEntity.setPrescription("Test Content");
        testEntity.setFollowUpNeeded(false);
        Consultation saved = repository.save(newEntity);
        assertNotNull(saved.getId(), "Entitatea salvată trebuie să aibă ID");
    }

    @Test
    void testDeleteById() {
        repository.deleteById(testEntity.getId());
        assertTrue(repository.findById(testEntity.getId()).isEmpty(), "Entitatea trebuie să fie ștearsă");
    }
}
