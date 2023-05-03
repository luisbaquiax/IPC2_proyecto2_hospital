import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportTopEspecialidadesComponent } from './report-top-especialidades.component';

describe('ReportTopEspecialidadesComponent', () => {
  let component: ReportTopEspecialidadesComponent;
  let fixture: ComponentFixture<ReportTopEspecialidadesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportTopEspecialidadesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportTopEspecialidadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
