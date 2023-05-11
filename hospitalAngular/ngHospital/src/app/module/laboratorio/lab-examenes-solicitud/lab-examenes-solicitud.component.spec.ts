import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LabExamenesSolicitudComponent } from './lab-examenes-solicitud.component';

describe('LabExamenesSolicitudComponent', () => {
  let component: LabExamenesSolicitudComponent;
  let fixture: ComponentFixture<LabExamenesSolicitudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LabExamenesSolicitudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LabExamenesSolicitudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
