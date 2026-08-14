import {Component, effect, inject, OnInit, signal} from '@angular/core';
import {VmaService} from "../service/vma.service";
import {Stomp} from "@stomp/stompjs";
import SockJS from "sockjs-client";
import {Category} from "../domain/category";
import {SongVote} from "../domain/song.vote";
import {ArtistVote} from "../domain/artist.vote";
import {CookieService} from "ngx-cookie-service";
import {MatCardModule} from '@angular/material/card';
import {MatRadioModule} from '@angular/material/radio';
import {FormsModule} from '@angular/forms';
import {FlexModule} from '@angular/flex-layout';
import {MatButton} from "@angular/material/button";
import {toSignal} from "@angular/core/rxjs-interop";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.less'],
  standalone: true,
  imports: [MatCardModule, MatRadioModule, FormsModule, FlexModule, MatButton]
})
export class MainComponent implements OnInit {

  private vmaService = inject(VmaService);
  private cookieService = inject(CookieService);

  private readonly stompClient;
  connected = signal(false);
  categories = signal<Category[]>([]);

  votingIdResource = toSignal(this.vmaService.generateUserVotingId());

  votingId = signal<string | undefined>(undefined);

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
    // Using a simple subscription for initial load of categories once votingId is available
    // Alternatively we could use another rxResource dependent on votingId.value()
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
        return cat
      })
      this.categories.set(categories);
    }
  }

  castVotes() {
    this.categories().filter(cat => cat.type === "INSTRUMENTAL" || cat.type === "SONG")
      .map(cat => {
        if (cat.selectedSong)
          this.vmaService.sendSongVote(
            {
              userId: this.votingIdResource()?.id,
              idC: cat.id,
              idS: cat.selectedSong
            } as SongVote).subscribe(_ => cat.voted = true)
      })
    this.categories().filter(cat => cat.type === "ARTIST")
      .map(cat => {
        if (cat.selectedArtist)
          this.vmaService.sendArtistVote(
            {
              userId: this.votingIdResource()?.id,
              idC: cat.id,
              idA: cat.selectedArtist
            } as ArtistVote).subscribe(_ => cat.voted = true)
      })
    this.vmaService.getCurrent()
      .subscribe(data => this.processVma(data))
  }
}
