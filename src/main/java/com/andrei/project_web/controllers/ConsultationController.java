package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.ConsultationDTO;
import com.andrei.project_web.service.ConsultationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    private final ConsultationService consultationService;

    @Autowired
    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultationDTO>> getAllConsultations() {
        return ResponseEntity.ok(consultationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable Long id) {
        return ResponseEntity.ok(consultationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ConsultationDTO> createConsultation(@RequestBody @Valid ConsultationDTO consultationDTO) {
        ConsultationDTO saved = consultationService.save(consultationDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultation(@PathVariable Long id, @RequestBody @Valid ConsultationDTO consultationDTO) {
        consultationDTO.setId(id);
        return ResponseEntity.ok(consultationService.save(consultationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
