package com.andrei.project_web.domain.security;

import com.andrei.project_web.domain.Appointment;
import com.andrei.project_web.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Role role;

    @OneToMany(mappedBy = "authority")
    @Builder.Default
    private List<User> users = new ArrayList<>();
}
