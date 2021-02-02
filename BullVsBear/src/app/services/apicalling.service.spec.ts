import { TestBed } from '@angular/core/testing';

import { ApicallingService } from './apicalling.service';

describe('ApicallingService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ApicallingService = TestBed.get(ApicallingService);
    expect(service).toBeTruthy();
  });
});
