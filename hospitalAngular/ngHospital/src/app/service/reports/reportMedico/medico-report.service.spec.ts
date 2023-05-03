import { TestBed } from '@angular/core/testing';

import { MedicoReportService } from './medico-report.service';

describe('MedicoReportService', () => {
  let service: MedicoReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicoReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
