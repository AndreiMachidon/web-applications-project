package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.RoomDTO;
import com.andrei.project_web.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @Test
    public void testGetAllRooms() {
        List<RoomDTO> rooms = List.of(new RoomDTO(), new RoomDTO());
        when(roomService.findAll()).thenReturn(rooms);

        ResponseEntity<List<RoomDTO>> response = roomController.getAllRooms();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetRoomById() {
        Long id = 1L;
        RoomDTO dto = new RoomDTO();
        dto.setId(id);
        when(roomService.findById(id)).thenReturn(dto);

        ResponseEntity<RoomDTO> response = roomController.getRoomById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testCreateRoom() {
        RoomDTO dto = new RoomDTO();
        dto.setId(1L);

        when(roomService.save(dto)).thenReturn(dto);

        ResponseEntity<RoomDTO> response = roomController.createRoom(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testUpdateRoom() {
        Long id = 1L;
        RoomDTO dto = new RoomDTO();
        dto.setId(id);

        when(roomService.save(dto)).thenReturn(dto);

        ResponseEntity<RoomDTO> response = roomController.updateRoom(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testDeleteRoom() {
        Long id = 1L;

        ResponseEntity<Void> response = roomController.deleteRoom(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(roomService).deleteById(id);
    }
}
