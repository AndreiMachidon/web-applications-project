package com.andrei.project_web.mappers;

import com.andrei.project_web.domain.Patient;
import com.andrei.project_web.domain.Room;
import com.andrei.project_web.dto.PatientDTO;
import com.andrei.project_web.dto.RoomDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDTO toDTO(Room room);
    Room toRoom(RoomDTO roomDTO);
}
