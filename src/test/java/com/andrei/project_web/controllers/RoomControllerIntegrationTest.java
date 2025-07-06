package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.RoomDTO;
import com.andrei.project_web.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class RoomControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RoomService roomService;

    private final Long fixedId = 1L;

    @Test
    public void testGetAllRooms() throws Exception {
        RoomDTO dto = RoomDTO.builder().id(fixedId).id(1L).build();
        when(roomService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    public void testGetRoomById() throws Exception {
        RoomDTO dto = RoomDTO.builder().id(fixedId).id(1L).build();
        when(roomService.findById(fixedId)).thenReturn(dto);

        mockMvc.perform(get("/api/rooms/{id}", fixedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testCreateRoom() throws Exception {
        RoomDTO dto = RoomDTO.builder().id(fixedId).id(1L).build();
        when(roomService.save(any())).thenReturn(dto);

        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "Camera Nouă"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testUpdateRoom() throws Exception {
        RoomDTO dto = RoomDTO.builder().id(fixedId).id(1L).build();
        when(roomService.save(any())).thenReturn(dto);

        mockMvc.perform(put("/api/rooms/{id}", fixedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "Camera Actualizată"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testDeleteRoom() throws Exception {
        mockMvc.perform(delete("/api/rooms/{id}", fixedId))
                .andExpect(status().isNoContent());

        verify(roomService).deleteById(fixedId);
    }
}
