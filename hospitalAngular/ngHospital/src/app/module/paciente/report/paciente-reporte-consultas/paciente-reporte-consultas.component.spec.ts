import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteReporteConsultasComponent } from './paciente-reporte-consultas.component';

describe('PacienteReporteConsultasComponent', () => {
  let component: PacienteReporteConsultasComponent;
  let fixture: ComponentFixture<PacienteReporteConsultasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteReporteConsultasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PacienteReporteConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
