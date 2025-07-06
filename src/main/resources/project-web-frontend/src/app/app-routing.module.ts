import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientLayoutComponent } from './components/patient/patient-layout/patient-layout.component';
import { DoctorLayoutComponent } from './components/doctor/doctor-layout/doctor-layout.component';
import { LoginComponent } from './components/login/login.component';
import { DoctorAppointmentsComponent } from './components/doctor/doctor-appointments/doctor-appointments.component';
import { DoctorDashboardComponent } from './components/doctor/doctor-dashboard/doctor-dashboard.component';
import { DoctorRecordsComponent } from './components/doctor/doctor-records/doctor-records.component';
import { DoctorRoomsComponent } from './components/doctor/doctor-rooms/doctor-rooms.component';
import { DoctorScheduleComponent } from './components/doctor/doctor-schedule/doctor-schedule.component';
import { PatientAppointmentsComponent } from './components/patient/patient-appointments/patient-appointments.component';
import { PatientDashboardComponent } from './components/patient/patient-dashboard/patient-dashboard.component';
import { PatientDoctorsComponent } from './components/patient/patient-doctors/patient-doctors.component';
import { PatientRecordsComponent } from './components/patient/patient-records/patient-records.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },

  {
    path: 'doctor',
    component: DoctorLayoutComponent,
    children: [
      { path: 'dashboard', component: DoctorDashboardComponent },
      { path: 'appointments', component: DoctorAppointmentsComponent },
      { path: 'records', component: DoctorRecordsComponent },
      { path: 'schedule', component: DoctorScheduleComponent },
      { path: 'rooms', component: DoctorRoomsComponent },
    ],
  },

  {
    path: 'patient',
    component: PatientLayoutComponent,
    children: [
      { path: 'dashboard', component: PatientDashboardComponent },
      { path: 'appointments', component: PatientAppointmentsComponent },
      { path: 'records', component: PatientRecordsComponent },
      { path: 'doctors', component: PatientDoctorsComponent },
    ],
  },

  { path: '**', redirectTo: '/login' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

 }
