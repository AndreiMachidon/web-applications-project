import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorRoomsComponent } from './doctor-rooms.component';

describe('DoctorRoomsComponent', () => {
  let component: DoctorRoomsComponent;
  let fixture: ComponentFixture<DoctorRoomsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DoctorRoomsComponent]
    });
    fixture = TestBed.createComponent(DoctorRoomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
