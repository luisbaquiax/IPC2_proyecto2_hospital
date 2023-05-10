import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LabEditExamenComponent } from './lab-edit-examen.component';

describe('LabEditExamenComponent', () => {
  let component: LabEditExamenComponent;
  let fixture: ComponentFixture<LabEditExamenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LabEditExamenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LabEditExamenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
