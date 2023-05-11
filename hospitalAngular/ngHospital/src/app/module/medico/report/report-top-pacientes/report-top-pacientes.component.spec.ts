import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportTopPacientesComponent } from './report-top-pacientes.component';

describe('ReportTopPacientesComponent', () => {
  let component: ReportTopPacientesComponent;
  let fixture: ComponentFixture<ReportTopPacientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportTopPacientesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportTopPacientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
