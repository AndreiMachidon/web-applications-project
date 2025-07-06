import { Component, OnInit } from '@angular/core';
import { DoctorServiceService } from 'src/app/services/doctor-service.service';

@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent implements OnInit {

  appointments: {
    patientName: string;
    date: string;
    consultationSummary: string;
  }[] = [];

  schedule: {
    room: string;
    date: string;
  }[] = [];

  medicalRecords: {
    id: number;
    patientName: string;
  }[] = [];

  constructor(private doctorService: DoctorServiceService) {}

  ngOnInit(): void {
    this.doctorService.getUpcomingAppointments().subscribe(data => this.appointments = data);
    this.doctorService.getTodaySchedule().subscribe(data => this.schedule = data);
    this.doctorService.getPatientRecords().subscribe(data => this.medicalRecords = data);
  }

  openRecord(id: number): void {
    // Navigare sau logica pentru a deschide fișa medicală
    console.log(`Opening medical record with ID: ${id}`);
  }
}
