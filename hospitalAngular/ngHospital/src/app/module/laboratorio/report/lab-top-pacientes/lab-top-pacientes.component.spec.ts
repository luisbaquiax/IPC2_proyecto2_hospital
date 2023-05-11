import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LabTopPacientesComponent } from './lab-top-pacientes.component';

describe('LabTopPacientesComponent', () => {
  let component: LabTopPacientesComponent;
  let fixture: ComponentFixture<LabTopPacientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LabTopPacientesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LabTopPacientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
