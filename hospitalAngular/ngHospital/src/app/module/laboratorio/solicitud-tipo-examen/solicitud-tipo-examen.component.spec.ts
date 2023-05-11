import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudTipoExamenComponent } from './solicitud-tipo-examen.component';

describe('SolicitudTipoExamenComponent', () => {
  let component: SolicitudTipoExamenComponent;
  let fixture: ComponentFixture<SolicitudTipoExamenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolicitudTipoExamenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitudTipoExamenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
