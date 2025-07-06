package com.andrei.project_web.repository;

import com.andrei.project_web.domain.Schedule;
import com.andrei.project_web.repositories.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository repository;

    private Schedule testEntity;

    @BeforeEach
    void setup() {
        testEntity = new Schedule();
        testEntity.setScheduleDateTime(LocalDateTime.now().plusDays(20));
        testEntity.setRoom(null);
        testEntity.setDoctor(null);
        repository.save(testEntity);
    }

    @Test
    void testFindAll() {
        List<Schedule> list = repository.findAll();
        assertFalse(list.isEmpty(), "Lista nu trebuie să fie goală");
    }

    @Test
    void testFindById() {
        Optional<Schedule> found = repository.findById(testEntity.getId());
        assertTrue(found.isPresent(), "Entitatea trebuie găsită după ID");
    }

    @Test
    void testSave() {
        Schedule newEntity = new Schedule();
        testEntity.setScheduleDateTime(LocalDateTime.now().plusDays(20));
        testEntity.setRoom(null);
        testEntity.setDoctor(null);
        Schedule saved = repository.save(newEntity);
        assertNotNull(saved.getId(), "Entitatea salvată trebuie să aibă ID");
    }

    @Test
    void testDeleteById() {
        repository.deleteById(testEntity.getId());
        assertTrue(repository.findById(testEntity.getId()).isEmpty(), "Entitatea trebuie să fie ștearsă");
    }
}

