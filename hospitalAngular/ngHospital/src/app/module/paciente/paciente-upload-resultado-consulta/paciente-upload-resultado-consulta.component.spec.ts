import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PacienteUploadResultadoConsultaComponent } from './paciente-upload-resultado-consulta.component';

describe('PacienteDownloadResultadoConsultaComponent', () => {
  let component: PacienteUploadResultadoConsultaComponent;
  let fixture: ComponentFixture<PacienteUploadResultadoConsultaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PacienteUploadResultadoConsultaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PacienteUploadResultadoConsultaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
