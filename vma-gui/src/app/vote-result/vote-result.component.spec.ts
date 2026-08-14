import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { CookieService } from 'ngx-cookie-service';

import { VoteResultComponent } from './vote-result.component';

describe('VoteResultComponent', () => {
  let component: VoteResultComponent;
  let fixture: ComponentFixture<VoteResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ VoteResultComponent ],
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        CookieService
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VoteResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
