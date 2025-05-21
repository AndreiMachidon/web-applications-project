package com.andrei.project_web.mappers;

import com.andrei.project_web.domain.Room;
import com.andrei.project_web.domain.Schedule;
import com.andrei.project_web.dto.RoomDTO;
import com.andrei.project_web.dto.ScheduleDTO;
import org.mapstruct.Mapper;
import org.springframework.scheduling.config.ScheduledTask;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    ScheduleDTO toDTO(Schedule schedule);
    Schedule toSchedule(ScheduleDTO scheduleDTO);
}
