import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {handleError} from './error.handler';
import {catchError, Observable, retry} from "rxjs";
import {VotingId} from "../domain/voting.id";

const localUrlRegistry = '/api/vma/voting/open';

@Injectable({
  providedIn: 'root'
})
export class VmaService {

  constructor(private http: HttpClient) {
  }

  generateUserVotingId(): Observable<VotingId> {
    return this.http.get<VotingId>(localUrlRegistry).pipe(
      retry(3), catchError(handleError<VotingId>()))
  }

}

