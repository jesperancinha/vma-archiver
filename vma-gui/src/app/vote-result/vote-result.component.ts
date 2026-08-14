import {Component, effect, inject, OnInit, signal} from '@angular/core';
import {Category} from "../domain/category";
import {VmaService} from "../service/vma.service";
import {CookieService} from "ngx-cookie-service";
import * as SockJS from "sockjs-client";
import {Stomp} from "@stomp/stompjs";
import {MatCardModule} from '@angular/material/card';
import {FlexModule} from '@angular/flex-layout';
import {toSignal} from "@angular/core/rxjs-interop";

@Component({
  selector: 'app-vote-result',
  templateUrl: './vote-result.component.html',
  styleUrls: ['./vote-result.component.less'],
  standalone: true,
  imports: [MatCardModule, FlexModule]
})
export class VoteResultComponent implements OnInit {

  private vmaService = inject(VmaService);
  private cookieService = inject(CookieService);

  private readonly stompClient;
  connected = signal(false);
  categories = signal<Category[]>([]);

  votingIdResource = toSignal(this.vmaService.generateUserVotingId());

  constructor() {
    const socket = new SockJS('/api/vma/broker');
    this.stompClient = Stomp.over(socket);

    effect(() => {
      const data = this.votingIdResource();
      if (data) {
        this.cookieService.set("votingId", data.id);
        this.vmaService.getCurrent().subscribe(categories => this.processVma(categories));
      }
    });
  }

  ngOnInit(): void {
    this.connect()
  }


  connect() {
    this.stompClient.connect({}, (frame: any) => {
      this.connected.set(true);
      this.stompClient.subscribe('/topic/vma', (vmas) => {
        console.log(vmas.body)
        this.processVma(JSON.parse(vmas.body) as Category[]);
      });
    });
  }

  setConnected(connected: boolean) {
    this.connected.set(connected)
  }

  processVma(categories: Category[]) {
    if (categories) {
      categories.map(cat => {
        let oldCat = this.categories().filter(catty => catty.id == cat.id).pop();
        if (oldCat) {
          cat.selectedArtist = oldCat.selectedArtist;
          cat.selectedSong = oldCat.selectedSong;
          cat.voted = oldCat.voted
        }
        if (cat.artists) {
          cat.artists.forEach(artist => {
            this.vmaService.getArtistVoteCount(cat.id, artist.id).subscribe(data => artist.votes = data)
          })
        }
        if (cat.songs) {
          cat.songs.forEach(song => {
            this.vmaService.getSongVoteCount(cat.id, song.id).subscribe(data => song.votes = data)
          })
        }
        return cat
      })
      this.categories.set(categories);
    }
  }
}
