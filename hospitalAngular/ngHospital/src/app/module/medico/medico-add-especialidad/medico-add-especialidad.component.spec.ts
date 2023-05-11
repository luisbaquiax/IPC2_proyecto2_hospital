import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalEditEspecialidadComponent } from './modal-edit-especialidad.component';

describe('MedicoAddEspecialidadComponent', () => {
  let component: ModalEditEspecialidadComponent;
  let fixture: ComponentFixture<ModalEditEspecialidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalEditEspecialidadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalEditEspecialidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
