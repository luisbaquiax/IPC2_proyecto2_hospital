import { TestBed } from '@angular/core/testing';

import { SolicitudExamenService } from './solicitud-examen.service';

describe('SolicitudExamenService', () => {
  let service: SolicitudExamenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SolicitudExamenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
