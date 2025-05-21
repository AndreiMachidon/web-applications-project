package com.andrei.project_web.mappers;

import com.andrei.project_web.domain.MedicalRecord;
import com.andrei.project_web.domain.Notification;
import com.andrei.project_web.dto.MedicalRecordDTO;
import com.andrei.project_web.dto.NotificationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDTO toDTO(Notification notification);
    Notification toNotification(NotificationDTO notificationDTO);
}
