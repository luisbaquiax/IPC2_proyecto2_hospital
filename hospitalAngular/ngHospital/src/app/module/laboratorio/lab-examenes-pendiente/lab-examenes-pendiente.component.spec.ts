import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LabExamenesPendienteComponent } from './lab-examenes-pendiente.component';

describe('LabExamenesPendienteComponent', () => {
  let component: LabExamenesPendienteComponent;
  let fixture: ComponentFixture<LabExamenesPendienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LabExamenesPendienteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LabExamenesPendienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
