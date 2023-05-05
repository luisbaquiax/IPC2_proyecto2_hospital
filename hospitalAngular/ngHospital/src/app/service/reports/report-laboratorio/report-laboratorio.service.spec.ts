import { TestBed } from '@angular/core/testing';

import { ReportLaboratorioService } from './report-laboratorio.service';

describe('ReportLaboratorioService', () => {
  let service: ReportLaboratorioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportLaboratorioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
