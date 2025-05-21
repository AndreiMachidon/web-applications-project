package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.AppointmentDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.service.AppointmentService;
import com.andrei.project_web.service.DoctorService;
import com.andrei.project_web.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService,
                                 DoctorService doctorService,
                                 PatientService patientService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("")
    public String listAppointments(Model model) {
        List<AppointmentDTO> appointments = appointmentService.findAll();
        model.addAttribute("appointments", appointments);
        return "appointmentList"; // ➜ resources/templates/appointmentList.html
    }

    @GetMapping("/form")
    public String showCreateForm(Model model) {
        model.addAttribute("appointment", new AppointmentDTO());
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("patients", patientService.findAll());
        return "appointmentForm"; // ➜ resources/templates/appointmentForm.html
    }

    @PostMapping("")
    public String saveOrUpdate(@Valid @ModelAttribute("appointment") AppointmentDTO appointment,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("doctors", doctorService.findAll());
            model.addAttribute("patients", patientService.findAll());
            return "appointmentForm";
        }

        appointmentService.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        AppointmentDTO appointment = appointmentService.findById(id);
        model.addAttribute("appointment", appointment);
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("patients", patientService.findAll());
        return "appointmentForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentService.deleteById(id);
        return "redirect:/appointments";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleNotFound(Exception ex) {
        ModelAndView mav = new ModelAndView("notFoundException");
        mav.addObject("exception", ex);
        return mav;
    }
}
