import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LabTopExamenesComponent } from './lab-top-examenes.component';

describe('LabTopExamenesComponent', () => {
  let component: LabTopExamenesComponent;
  let fixture: ComponentFixture<LabTopExamenesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LabTopExamenesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LabTopExamenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
