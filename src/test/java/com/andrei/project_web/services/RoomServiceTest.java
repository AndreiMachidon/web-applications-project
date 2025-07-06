package com.andrei.project_web.services;

import com.andrei.project_web.domain.Room;
import com.andrei.project_web.dto.RoomDTO;
import com.andrei.project_web.mappers.RoomMapper;
import com.andrei.project_web.repositories.RoomRepository;
import com.andrei.project_web.service.RoomService;
import com.andrei.project_web.service.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomMapper roomMapper;

    @InjectMocks
    private RoomServiceImpl roomServiceImpl;

    @Test
    public void testFindAll() {
        when(roomRepository.findAll()).thenReturn(List.of(new Room()));
        when(roomMapper.toDTO(any())).thenReturn(new RoomDTO());

        List<RoomDTO> result = roomServiceImpl.findAll();

        assertEquals(1, result.size());
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Room entity = new Room();
        entity.setId(1L);
        when(roomRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(roomMapper.toDTO(entity)).thenReturn(new RoomDTO());

        RoomDTO result = roomServiceImpl.findById(1L);

        assertNotNull(result);
        verify(roomRepository).findById(1L);
    }

    @Test
    public void testSave() {
        RoomDTO dto = new RoomDTO();
        Room entity = new Room();

        when(roomMapper.toRoom(dto)).thenReturn(entity);
        when(roomRepository.save(entity)).thenReturn(entity);
        when(roomMapper.toDTO(entity)).thenReturn(dto);

        RoomDTO saved = roomServiceImpl.save(dto);

        assertNotNull(saved);
        verify(roomRepository).save(entity);
    }

    @Test
    public void testDeleteById() {
        roomServiceImpl.deleteById(1L);
        verify(roomRepository).deleteById(1L);
    }
}
