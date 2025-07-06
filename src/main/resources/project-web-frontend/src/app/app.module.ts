import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { DoctorLayoutComponent } from './components/doctor/doctor-layout/doctor-layout.component';
import { DoctorDashboardComponent } from './components/doctor/doctor-dashboard/doctor-dashboard.component';
import { DoctorAppointmentsComponent } from './components/doctor/doctor-appointments/doctor-appointments.component';
import { DoctorRecordsComponent } from './components/doctor/doctor-records/doctor-records.component';
import { DoctorScheduleComponent } from './components/doctor/doctor-schedule/doctor-schedule.component';
import { DoctorRoomsComponent } from './components/doctor/doctor-rooms/doctor-rooms.component';
import { PatientLayoutComponent } from './components/patient/patient-layout/patient-layout.component';
import { PatientDashboardComponent } from './components/patient/patient-dashboard/patient-dashboard.component';
import { PatientAppointmentsComponent } from './components/patient/patient-appointments/patient-appointments.component';
import { PatientRecordsComponent } from './components/patient/patient-records/patient-records.component';
import { PatientDoctorsComponent } from './components/patient/patient-doctors/patient-doctors.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { JwtInterceptor } from './interceptors/jwt.service';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DoctorLayoutComponent,
    DoctorDashboardComponent,
    DoctorAppointmentsComponent,
    DoctorRecordsComponent,
    DoctorScheduleComponent,
    DoctorRoomsComponent,
    PatientLayoutComponent,
    PatientDashboardComponent,
    PatientAppointmentsComponent,
    PatientRecordsComponent,
    PatientDoctorsComponent
  ],
  imports: [
    BrowserModule,
    NoopAnimationsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatIconModule,
    RouterModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    MatCardModule,
    MatListModule,
  ],
    providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
