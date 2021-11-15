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
const localUrlArtist = "/api/vma/voting/artist"
const localUrlSong = "/api/vma/voting/song"

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
    return this.http.post(localUrlArtist, param)
      .pipe(retry(3), catchError(handleError()));
  }

  sendSongVote(songVote: SongVote) {
    return this.http.post(localUrlSong, songVote)
      .pipe(retry(3), catchError(handleError()));
  }

  getArtistVoteCount(idc: string, ida: string): Observable<number> {
    return this.http.get<number>(localUrlArtist + "/" + idc + "/" + ida).pipe(
      retry(3), catchError(handleError<number>()))
  }

  getSongVoteCount(idc: string, ids: string): Observable<number> {
    return this.http.get<number>(localUrlSong + "/" + idc + "/" + ids).pipe(
      retry(3), catchError(handleError<number>()))
  }
}

