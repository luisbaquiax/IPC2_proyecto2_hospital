import { TestBed } from '@angular/core/testing';

import { DownloadMedicoService } from './download-medico.service';

describe('DownloadMedicoService', () => {
  let service: DownloadMedicoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DownloadMedicoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
