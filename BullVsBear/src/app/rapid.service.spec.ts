import { TestBed } from '@angular/core/testing';

import { RapidService } from './rapid.service';

describe('RapidService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RapidService = TestBed.get(RapidService);
    expect(service).toBeTruthy();
  });
});
