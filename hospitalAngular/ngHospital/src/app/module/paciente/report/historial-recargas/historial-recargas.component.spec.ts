import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteHistorialRecargasComponent } from './historial-recargas.component';

describe('HistorialRecargasComponent', () => {
  let component: PacienteHistorialRecargasComponent;
  let fixture: ComponentFixture<PacienteHistorialRecargasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteHistorialRecargasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PacienteHistorialRecargasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
