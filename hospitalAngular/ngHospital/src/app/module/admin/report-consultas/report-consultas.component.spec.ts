import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportConsultasComponent } from './report-consultas.component';

describe('ReportConsultasComponent', () => {
  let component: ReportConsultasComponent;
  let fixture: ComponentFixture<ReportConsultasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportConsultasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
