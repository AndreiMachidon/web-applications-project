package com.andrei.project_web.domain;

import com.andrei.project_web.domain.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private LocalDateTime sentAt;

    @Column(name = "is_read")
    private boolean read;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
