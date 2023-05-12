import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteConsultasPendienteResultadosComponent } from './paciente-consultas-pendiente-resultados.component';

describe('PacienteConsultasPendienteResultadosComponent', () => {
  let component: PacienteConsultasPendienteResultadosComponent;
  let fixture: ComponentFixture<PacienteConsultasPendienteResultadosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteConsultasPendienteResultadosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PacienteConsultasPendienteResultadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
