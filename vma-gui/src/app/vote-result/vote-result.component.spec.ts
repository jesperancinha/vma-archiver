import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoteResultComponent } from './vote-result.component';

describe('VoteResultComponent', () => {
  let component: VoteResultComponent;
  let fixture: ComponentFixture<VoteResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VoteResultComponent ]
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
