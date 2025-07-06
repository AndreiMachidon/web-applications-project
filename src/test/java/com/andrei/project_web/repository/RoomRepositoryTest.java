package com.andrei.project_web.repository;

import com.andrei.project_web.domain.Room;
import com.andrei.project_web.domain.enums.RoomType;
import com.andrei.project_web.repositories.RoomRepository;
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
public class RoomRepositoryTest {
    @Autowired
    private RoomRepository repository;

    private Room testEntity;

    @BeforeEach
    void setup() {
        testEntity = new Room();
        testEntity.setRoomNumber(20);
        testEntity.setType(RoomType.LAB);
        testEntity.setCapacity(1);
        testEntity.setClinic(null);
        repository.save(testEntity);
    }

    @Test
    void testFindAll() {
        List<Room> list = repository.findAll();
        assertFalse(list.isEmpty(), "Lista nu trebuie să fie goală");
    }

    @Test
    void testFindById() {
        Optional<Room> found = repository.findById(testEntity.getId());
        assertTrue(found.isPresent(), "Entitatea trebuie găsită după ID");
    }

    @Test
    void testSave() {
        Room newEntity = new Room();
        testEntity.setRoomNumber(20);
        testEntity.setType(com.andrei.project_web.domain.enums.RoomType.EXAM);
        testEntity.setCapacity(1);
        testEntity.setClinic(null);
        Room saved = repository.save(newEntity);
        assertNotNull(saved.getId(), "Entitatea salvată trebuie să aibă ID");
    }

    @Test
    void testDeleteById() {
        repository.deleteById(testEntity.getId());
        assertTrue(repository.findById(testEntity.getId()).isEmpty(), "Entitatea trebuie să fie ștearsă");
    }
}
