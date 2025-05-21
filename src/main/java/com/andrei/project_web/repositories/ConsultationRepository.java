package com.andrei.project_web.repositories;

import com.andrei.project_web.domain.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
