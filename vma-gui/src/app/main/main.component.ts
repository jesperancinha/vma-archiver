import {Component, OnInit} from '@angular/core';
import {VmaService} from "../service/vma.service";
import {Stomp} from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import {Category} from "../domain/category";
import {SongVote} from "../domain/song.vote";
import {ArtistVote} from "../domain/artist.vote";
import {CookieService} from "ngx-cookie-service";
import {MatCardModule} from '@angular/material/card';
import {MatRadioModule} from '@angular/material/radio';
import {FormsModule} from '@angular/forms';
import {FlexModule} from '@angular/flex-layout';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.less'],
  standalone: true,
  imports: [MatCardModule, MatRadioModule, FormsModule, FlexModule]
})
export class MainComponent implements OnInit {

  private readonly stompClient;
  private connected: boolean = false;
  categories: Category[] = [];
  votingId?: string

  constructor(private vmaService: VmaService,
              private cookieService: CookieService) {
    const socket = new SockJS('/api/vma/broker');
    this.stompClient = Stomp.over(socket);
  }

  ngOnInit(): void {
    this.connect()
    this.vmaService.generateUserVotingId()
      .subscribe(data => {
        if(data) {
          this.votingId = data.id
          this.cookieService.set("votingId", this.votingId)
          this.vmaService.getCurrent()
            .subscribe(data => this.processVma(data))
        }
      })

  }

  connect() {
    const _this = this;
    this.stompClient.connect({}, function (frame: any) {
      _this.setConnected(true);
      _this.stompClient.subscribe('/topic/vma', function (vmas) {
        console.log(vmas.body)
        _this.processVma(JSON.parse(vmas.body) as Category[]);
      });
    });
  }

  setConnected(connected: boolean) {
    this.connected = connected
  }

  processVma(categories: Category[]) {
    if (categories) {
      categories.map(cat => {
        let oldCat = this.categories.filter(catty => catty.id == cat.id).pop();
        if (oldCat) {
          cat.selectedArtist = oldCat.selectedArtist;
          cat.selectedSong = oldCat.selectedSong;
          cat.voted = oldCat.voted
        }
        return cat
      })
      this.categories = categories;
    }
  }

  castVotes() {
    this.categories.filter(cat => cat.type === "INSTRUMENTAL" || cat.type === "SONG")
      .map(cat => {
        if (cat.selectedSong)
          this.vmaService.sendSongVote(
            {
              userId: this.votingId,
              idC: cat.id,
              idS: cat.selectedSong
            } as SongVote).subscribe(_ => cat.voted = true)
      })
    this.categories.filter(cat => cat.type === "ARTIST")
      .map(cat => {
        if (cat.selectedArtist)
          this.vmaService.sendArtistVote(
            {
              userId: this.votingId,
              idC: cat.id,
              idA: cat.selectedArtist
            } as ArtistVote).subscribe(_ => cat.voted = true)
      })
    this.vmaService.getCurrent()
      .subscribe(data => this.processVma(data))
  }
}
