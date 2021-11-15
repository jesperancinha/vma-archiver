import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {handleError} from './error.handler';
import {catchError, Observable, retry} from "rxjs";
import {VotingId} from "../domain/voting.id";
import {Category} from "../domain/category";
import {SongVote} from "../domain/song.vote";
import {ArtistVote} from "../domain/artist.vote";

const localUrlVoting = '/api/vma/voting';
const localUrlRegistry = '/api/vma/registry';
const urlArtist = "/api/vma/voting/artist"
const urlSong = "/api/vma/voting/song"

@Injectable({
  providedIn: 'root'
})
export class VmaService {

  constructor(private http: HttpClient) {
  }

  generateUserVotingId(): Observable<VotingId> {
    return this.http.get<VotingId>(localUrlVoting + "/open").pipe(
      retry(3), catchError(handleError<VotingId>()))
  }

  getCurrent(): Observable<Category[]> {
    return this.http.get<Category[]>(localUrlRegistry + "/current").pipe(
      retry(3), catchError(handleError<Category[]>()))
  }

  sendArtistVote(param: ArtistVote) {
    return this.http.post(urlArtist, param)
      .pipe(retry(3), catchError(handleError()));
  }

  sendSongVote(songVote: SongVote) {
    return this.http.post(urlSong, songVote)
      .pipe(retry(3), catchError(handleError()));
  }
}

