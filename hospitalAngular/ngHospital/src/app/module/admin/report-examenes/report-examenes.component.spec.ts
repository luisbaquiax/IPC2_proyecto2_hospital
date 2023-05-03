import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportExamenesComponent } from './report-examenes.component';

describe('ReportExamenesComponent', () => {
  let component: ReportExamenesComponent;
  let fixture: ComponentFixture<ReportExamenesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportExamenesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportExamenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
