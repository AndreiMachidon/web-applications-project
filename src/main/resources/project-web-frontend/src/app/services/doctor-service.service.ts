import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DoctorServiceService {

 private base = `${URL}/doctor`;

   constructor(private http: HttpClient) {}

   getUpcomingAppointments(): Observable<any[]> {
    return this.http.get<any[]>(`${this.base}/appointments/upcoming`);
  }

   getTodaySchedule(): Observable<any[]> {
    return this.http.get<any[]>(`${this.base}/schedule/today`);
  }

   getPatientRecords(): Observable<any[]> {
    return this.http.get<any[]>(`${this.base}/patients/records`);
  }
}
