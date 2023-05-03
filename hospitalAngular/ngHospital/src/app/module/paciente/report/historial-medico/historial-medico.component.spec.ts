import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteHistorialMedicoComponent } from './historial-medico.component';

describe('HistorialMedicoComponent', () => {
  let component: PacienteHistorialMedicoComponent;
  let fixture: ComponentFixture<PacienteHistorialMedicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteHistorialMedicoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PacienteHistorialMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
