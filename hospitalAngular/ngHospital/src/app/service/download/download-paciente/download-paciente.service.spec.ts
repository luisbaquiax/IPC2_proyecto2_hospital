import { TestBed } from '@angular/core/testing';

import { DownloadPacienteService } from './download-paciente.service';

describe('DownloadPacienteService', () => {
  let service: DownloadPacienteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DownloadPacienteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
