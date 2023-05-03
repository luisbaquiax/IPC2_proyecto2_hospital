import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteReportConsultasComponent } from './report-consultas.component';

describe('ReportConsultasComponent', () => {
  let component: PacienteReportConsultasComponent;
  let fixture: ComponentFixture<PacienteReportConsultasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteReportConsultasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PacienteReportConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
