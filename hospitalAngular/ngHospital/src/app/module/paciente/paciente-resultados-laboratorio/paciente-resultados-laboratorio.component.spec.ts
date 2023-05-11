import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteResultadosLaboratorioComponent } from './paciente-resultados-laboratorio.component';

describe('PacienteResultadosLaboratorioComponent', () => {
  let component: PacienteResultadosLaboratorioComponent;
  let fixture: ComponentFixture<PacienteResultadosLaboratorioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteResultadosLaboratorioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PacienteResultadosLaboratorioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
