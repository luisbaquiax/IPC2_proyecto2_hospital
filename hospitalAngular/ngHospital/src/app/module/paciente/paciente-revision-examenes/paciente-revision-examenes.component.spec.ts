import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteRevisionExamenesComponent } from './paciente-revision-examenes.component';

describe('PacienteRevisionExamenesComponent', () => {
  let component: PacienteRevisionExamenesComponent;
  let fixture: ComponentFixture<PacienteRevisionExamenesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteRevisionExamenesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PacienteRevisionExamenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
