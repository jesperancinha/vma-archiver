import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoteFollowingComponent } from './vote-following.component';

describe('VoteFollowingComponent', () => {
  let component: VoteFollowingComponent;
  let fixture: ComponentFixture<VoteFollowingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VoteFollowingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VoteFollowingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
