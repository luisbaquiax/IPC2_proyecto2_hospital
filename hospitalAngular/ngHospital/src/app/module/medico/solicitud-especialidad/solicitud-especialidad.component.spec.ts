import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudEspecialidadComponent } from './solicitud-especialidad.component';

describe('SolicitudEspecialidadComponent', () => {
  let component: SolicitudEspecialidadComponent;
  let fixture: ComponentFixture<SolicitudEspecialidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolicitudEspecialidadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitudEspecialidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
