import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteReportExamenesComponent } from './paciente-report-examenes.component';

describe('PacienteReportExamenesComponent', () => {
  let component: PacienteReportExamenesComponent;
  let fixture: ComponentFixture<PacienteReportExamenesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteReportExamenesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PacienteReportExamenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
