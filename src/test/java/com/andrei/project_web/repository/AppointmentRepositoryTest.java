package com.andrei.project_web.repository;

import com.andrei.project_web.domain.Appointment;
import com.andrei.project_web.repositories.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository repository;

    private Appointment testEntity;

    @BeforeEach
    void setup() {
        testEntity = new Appointment();
        testEntity.setStartTime(LocalDateTime.now());
        testEntity.setEndTime(LocalDateTime.now().plusHours(2));
        testEntity.setStatus(com.andrei.project_web.domain.enums.Status.PENDING);
        testEntity.setPatient(null);
        testEntity.setDoctor(null);
        testEntity.setConsultation(null);
        repository.save(testEntity);
    }

    @Test
    void testFindAll() {
        List<Appointment> list = repository.findAll();
        assertFalse(list.isEmpty(), "Lista nu trebuie să fie goală");
    }

    @Test
    void testFindById() {
        Optional<Appointment> found = repository.findById(testEntity.getId());
        assertTrue(found.isPresent(), "Entitatea trebuie găsită după ID");
    }

    @Test
    void testSave() {
        Appointment newEntity = new Appointment();
        testEntity.setStartTime(LocalDateTime.now());
        testEntity.setEndTime(LocalDateTime.now().plusHours(2));
        testEntity.setStatus(com.andrei.project_web.domain.enums.Status.PENDING);
        testEntity.setPatient(null);
        testEntity.setDoctor(null);
        testEntity.setConsultation(null);
        Appointment saved = repository.save(newEntity);
        assertNotNull(saved.getId(), "Entitatea salvată trebuie să aibă ID");
    }

    @Test
    void testDeleteById() {
        repository.deleteById(testEntity.getId());
        assertTrue(repository.findById(testEntity.getId()).isEmpty(), "Entitatea trebuie să fie ștearsă");
    }
}
