import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicoConsultaPendienteRevisionComponent } from './medico-consulta-pendiente-revision.component';

describe('MedicoConsultaPendienteRevisionComponent', () => {
  let component: MedicoConsultaPendienteRevisionComponent;
  let fixture: ComponentFixture<MedicoConsultaPendienteRevisionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicoConsultaPendienteRevisionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicoConsultaPendienteRevisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
