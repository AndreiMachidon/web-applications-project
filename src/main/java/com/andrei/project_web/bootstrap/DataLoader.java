package com.andrei.project_web.bootstrap;

import com.andrei.project_web.domain.*;
import com.andrei.project_web.domain.enums.Role;
import com.andrei.project_web.domain.enums.RoomType;
import com.andrei.project_web.domain.enums.Status;
import com.andrei.project_web.domain.security.Authority;
import com.andrei.project_web.domain.security.User;
import com.andrei.project_web.repositories.*;
import com.andrei.project_web.repositories.security.AuthorityRepository;
import com.andrei.project_web.repositories.security.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Profile("mysql")
public class DataLoader implements CommandLineRunner {

    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ClinicRepository clinicRepository;
    private RoomRepository roomRepository;
    private ScheduleRepository scheduleRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private ConsultationRepository consultationRepository;
    private AppointmentRepository appointmentRepository;
    private NotificationRepository notificationRepository;


    private void loadUserData() {
        if (userRepository.count() == 0){
            Authority patientRole = authorityRepository.save(Authority.builder().role(Role.PATIENT).build());
            Authority doctorRole = authorityRepository.save(Authority.builder().role(Role.DOCTOR).build());
            Authority adminRole = authorityRepository.save(Authority.builder().role(Role.ADMIN).build());

            Doctor doctor = doctorRepository.save(Doctor.builder()
                    .name("Dr. House")
                    .email("house@example.com")
                    .specialization("Diagnostic")
                    .licenseNumber("H12345")
                    .build());

            Patient patient = patientRepository.save(Patient.builder()
                    .name("John Doe")
                    .email("john@example.com")
                    .birthDate(LocalDate.of(1990, 1, 1))
                    .phone("0720000000")
                    .address("123 Test Street")
                    .build());

            userRepository.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("12345"))
                    .authority(adminRole)
                    .build());

            userRepository.save(User.builder()
                    .username("doctor")
                    .password(passwordEncoder.encode("12345"))
                    .authority(doctorRole)
                    .doctor(doctor)
                    .build());

            userRepository.save(User.builder()
                    .username("patient")
                    .password(passwordEncoder.encode("12345"))
                    .authority(patientRole)
                    .patient(patient)
                    .build());
        }
    }

    private void loadTestData() {
        if (clinicRepository.count() == 0) {
            Clinic clinic = clinicRepository.save(Clinic.builder()
                    .name("Central Clinic")
                    .location("Downtown")
                    .phoneNumber("0310000000")
                    .build());

            Room room1 = roomRepository.save(Room.builder()
                    .roomNumber(101L)
                    .capacity(1)
                    .type(RoomType.EXAM)
                    .clinic(clinic)
                    .build());

            Room room2 = roomRepository.save(Room.builder()
                    .roomNumber(102L)
                    .capacity(2)
                    .type(RoomType.LAB)
                    .clinic(clinic)
                    .build());

            List<Doctor> doctors = doctorRepository.findAll();
            List<Patient> patients = patientRepository.findAll();

            if (!doctors.isEmpty() && !patients.isEmpty()) {
                Doctor doctor = doctors.get(0);
                Patient patient = patients.get(0);

                Schedule schedule = scheduleRepository.save(Schedule.builder()
                        .doctor(doctor)
                        .room(room1)
                        .scheduleDateTime(LocalDateTime.now().plusDays(1))
                        .build());

                MedicalRecord record = medicalRecordRepository.save(MedicalRecord.builder()
                        .description("Initial visit")
                        .diagnosis("Flu")
                        .creationDate(LocalDateTime.now())
                        .patient(patient)
                        .doctor(doctor)
                        .build());

                Consultation consultation = consultationRepository.save(Consultation.builder()
                        .summary("Basic consultation")
                        .prescription("Rest and hydration")
                        .followUpNeeded(false)
                        .build());

                Appointment appointment = appointmentRepository.save(Appointment.builder()
                        .startTime(LocalDateTime.now().plusDays(2))
                        .endTime(LocalDateTime.now().plusDays(2).plusHours(1))
                        .status(Status.CONFIRMED)
                        .patient(patient)
                        .doctor(doctor)
                        .consultation(consultation)
                        .build());

                consultation.setAppointment(appointment);
                consultationRepository.save(consultation);

                notificationRepository.save(Notification.builder()
                        .message("Your appointment is confirmed.")
                        .sentAt(LocalDateTime.now())
                        .read(false)
                        .user(patient.getUser())
                        .build());
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
        loadTestData();
    }
}
