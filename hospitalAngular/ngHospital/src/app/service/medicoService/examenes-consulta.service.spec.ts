import { TestBed } from '@angular/core/testing';

import { ExamenesConsultaService } from './examenes-consulta.service';

describe('ExamenesConsultaService', () => {
  let service: ExamenesConsultaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExamenesConsultaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
