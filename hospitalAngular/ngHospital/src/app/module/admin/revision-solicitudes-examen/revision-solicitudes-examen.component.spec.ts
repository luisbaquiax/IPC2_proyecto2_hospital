import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevisionSolicitudesExamenComponent } from './revision-solicitudes-examen.component';

describe('RevisionSolicitudesExamenComponent', () => {
  let component: RevisionSolicitudesExamenComponent;
  let fixture: ComponentFixture<RevisionSolicitudesExamenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RevisionSolicitudesExamenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RevisionSolicitudesExamenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
