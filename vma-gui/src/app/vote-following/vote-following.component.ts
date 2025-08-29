import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-vote-following',
  templateUrl: './vote-following.component.html',
  styleUrls: ['./vote-following.component.less'],
  standalone: true,
  imports: [MatCardModule]
})
export class VoteFollowingComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
