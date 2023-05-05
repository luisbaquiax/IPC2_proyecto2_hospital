import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevisionSolicitudesEspecialidadComponent } from './revision-solicitudes-especialidad.component';

describe('RevisionSolicitudesEspecialidadComponent', () => {
  let component: RevisionSolicitudesEspecialidadComponent;
  let fixture: ComponentFixture<RevisionSolicitudesEspecialidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RevisionSolicitudesEspecialidadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RevisionSolicitudesEspecialidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
