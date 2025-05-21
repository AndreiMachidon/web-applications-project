package com.andrei.project_web.service;

import com.andrei.project_web.domain.Room;
import com.andrei.project_web.dto.RoomDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.mappers.RoomMapper;
import com.andrei.project_web.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public List<RoomDTO> findAll() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO findById(long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        return roomMapper.toDTO(room);
    }

    @Override
    public RoomDTO save(RoomDTO dto) {
        Room saved = roomRepository.save(roomMapper.toRoom(dto));
        return roomMapper.toDTO(saved);
    }

    @Override
    public RoomDTO update(RoomDTO dto) {
        Room existing = roomRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        existing.setRoomNumber(dto.getRoomNumber());
        existing.setType(dto.getType());
        existing.setCapacity(dto.getCapacity());

        return roomMapper.toDTO(roomRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
}
