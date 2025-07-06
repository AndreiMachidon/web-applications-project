package com.andrei.project_web.repository;

import com.andrei.project_web.domain.Notification;
import com.andrei.project_web.repositories.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository repository;

    private Notification testEntity;

    @BeforeEach
    void setup() {
        testEntity = new Notification();
        testEntity.setMessage("Test Content");
        testEntity.setSentAt(LocalDateTime.now().minusMinutes(30));
        testEntity.setRead(false);
        testEntity.setUser(null);
        repository.save(testEntity);
    }

    @Test
    void testFindAll() {
        List<Notification> list = repository.findAll();
        assertFalse(list.isEmpty(), "Lista nu trebuie să fie goală");
    }

    @Test
    void testFindById() {
        Optional<Notification> found = repository.findById(testEntity.getId());
        assertTrue(found.isPresent(), "Entitatea trebuie găsită după ID");
    }

    @Test
    void testSave() {
        Notification newEntity = new Notification();
        testEntity.setMessage("Test Content");
        testEntity.setSentAt(LocalDateTime.now().minusMinutes(30));
        testEntity.setRead(false);
        testEntity.setUser(null);
        Notification saved = repository.save(newEntity);
        assertNotNull(saved.getId(), "Entitatea salvată trebuie să aibă ID");
    }

    @Test
    void testDeleteById() {
        repository.deleteById(testEntity.getId());
        assertTrue(repository.findById(testEntity.getId()).isEmpty(), "Entitatea trebuie să fie ștearsă");
    }
}
