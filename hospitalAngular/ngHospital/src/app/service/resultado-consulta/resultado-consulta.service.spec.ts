import { TestBed } from '@angular/core/testing';

import { ResultadoConsultaService } from './resultado-consulta.service';

describe('ResultadoConsultaService', () => {
  let service: ResultadoConsultaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResultadoConsultaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
