import {Component, OnInit} from '@angular/core';
import {VmaService} from "../service/vma.service";
import {Stomp} from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import {Category} from "../domain/category";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.less']
})
export class MainComponent implements OnInit {

  private readonly stompClient;
  private connected: boolean = false;
  categories: Category[] = [];
  votingId?: string

  constructor(private vmaService: VmaService, private cookieService: CookieService) {
    const socket = new SockJS('/api/vma/broker');
    this.stompClient = Stomp.over(socket);
  }

  ngOnInit(): void {
    this.connect()
    this.vmaService.generateUserVotingId()
      .subscribe(data => this.votingId = data[0])
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
    categories.map(cat => {
      let oldCat = this.categories.filter(catty => catty.id == cat.id).pop();
      if (oldCat) {
        cat.selectedArtist = oldCat.selectedArtist;
        cat.selectedSong = oldCat.selectedSong;
      }
      return cat
    })
    this.categories = categories;
  }
}
