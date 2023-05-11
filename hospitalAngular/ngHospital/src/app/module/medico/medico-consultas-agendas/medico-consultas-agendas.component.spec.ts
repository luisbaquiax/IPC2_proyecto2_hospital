import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicoConsultasAgendasComponent } from './medico-consultas-agendas.component';

describe('MedicoConsultasAgendasComponent', () => {
  let component: MedicoConsultasAgendasComponent;
  let fixture: ComponentFixture<MedicoConsultasAgendasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicoConsultasAgendasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicoConsultasAgendasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
