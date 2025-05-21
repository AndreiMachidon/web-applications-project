package com.andrei.project_web.dto;

import com.andrei.project_web.domain.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationDTO {
    private long id;
    private String message;
    private LocalDateTime sentAt;
    private boolean read;
    private User user;
}
