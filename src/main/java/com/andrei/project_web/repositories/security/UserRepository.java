package com.andrei.project_web.repositories.security;

import com.andrei.project_web.domain.security.User;
import org.mapstruct.control.MappingControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
