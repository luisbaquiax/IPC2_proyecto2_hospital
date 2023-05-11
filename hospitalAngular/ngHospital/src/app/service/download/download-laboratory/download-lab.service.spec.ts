import { TestBed } from '@angular/core/testing';

import { DownloadLabService } from './download-lab.service';

describe('DownloadLabService', () => {
  let service: DownloadLabService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DownloadLabService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
