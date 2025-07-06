package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.ClinicDTO;
import com.andrei.project_web.service.ClinicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinics")
public class ClinicController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        return ResponseEntity.ok(clinicService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicDTO> getClinicById(@PathVariable Long id) {
        return ResponseEntity.ok(clinicService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClinicDTO> createClinic(@RequestBody @Valid ClinicDTO clinicDTO) {
        ClinicDTO saved = clinicService.save(clinicDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicDTO> updateClinic(@PathVariable Long id, @RequestBody @Valid ClinicDTO clinicDTO) {
        clinicDTO.setId(id);
        return ResponseEntity.ok(clinicService.save(clinicDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        clinicService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
