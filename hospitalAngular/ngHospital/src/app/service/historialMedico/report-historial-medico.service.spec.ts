import { TestBed } from '@angular/core/testing';

import { HistorialMedicoService } from './report-historial-medico.service';

describe('ReportHistorialMedicoService', () => {
  let service: HistorialMedicoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HistorialMedicoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
