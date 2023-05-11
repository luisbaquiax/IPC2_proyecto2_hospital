import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteCreateSolicitudExamenComponent } from './paciente-create-solicitud-examen.component';

describe('PacienteCreateSolicitudExamenComponent', () => {
  let component: PacienteCreateSolicitudExamenComponent;
  let fixture: ComponentFixture<PacienteCreateSolicitudExamenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteCreateSolicitudExamenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PacienteCreateSolicitudExamenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
