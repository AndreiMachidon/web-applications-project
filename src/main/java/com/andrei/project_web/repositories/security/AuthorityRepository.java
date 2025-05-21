package com.andrei.project_web.repositories.security;

import com.andrei.project_web.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
