import {Component, OnInit} from '@angular/core';
import {Category} from "../domain/category";
import {VmaService} from "../service/vma.service";
import {CookieService} from "ngx-cookie-service";
import * as SockJS from "sockjs-client";
import {Stomp} from "@stomp/stompjs";

@Component({
  selector: 'app-vote-result',
  templateUrl: './vote-result.component.html',
  styleUrls: ['./vote-result.component.less']
})
export class VoteResultComponent implements OnInit {


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
    this.vmaService.generateUserVotingId()
      .subscribe(data => {
        this.votingId = data.id
        this.cookieService.set("votingId", this.votingId)
        this.vmaService.getCurrent()
          .subscribe(data => this.processVma(data))
      })

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
      console.log("categoriescategories")
      console.log(categories)
      this.categories = categories;
    }
  }
}
