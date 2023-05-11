import { TestBed } from '@angular/core/testing';

import { ResultadoLaboratorioService } from './resultado-laboratorio.service';

describe('ResultadoLaboratorioService', () => {
  let service: ResultadoLaboratorioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResultadoLaboratorioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
