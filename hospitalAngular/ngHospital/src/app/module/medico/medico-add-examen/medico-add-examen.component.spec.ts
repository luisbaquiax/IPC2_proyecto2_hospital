import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicoAddExamenComponent } from './medico-add-examen.component';

describe('MedicoAddExamenComponent', () => {
  let component: MedicoAddExamenComponent;
  let fixture: ComponentFixture<MedicoAddExamenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicoAddExamenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicoAddExamenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
