import { TestBed } from '@angular/core/testing';

import { HistorialRecargaService } from './historial-recarga.service';

describe('HistorialRecargaService', () => {
  let service: HistorialRecargaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HistorialRecargaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
