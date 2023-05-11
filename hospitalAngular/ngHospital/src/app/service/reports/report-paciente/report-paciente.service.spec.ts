import { TestBed } from '@angular/core/testing';

import { ReportPacienteService } from './report-paciente.service';

describe('ReportPacienteService', () => {
  let service: ReportPacienteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportPacienteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
