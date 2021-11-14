import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {handleError} from './error.handler';
import {catchError, Observable, retry} from "rxjs";

const localUrlRegistry = '/api/vma/voting/open';

@Injectable({
  providedIn: 'root'
})
export class VmaService {

  constructor(private http: HttpClient) {
  }

  generateUserVotingId(): Observable<string[]> {
    return this.http.get<string[]>(localUrlRegistry).pipe(
      retry(3), catchError(handleError<string[]>()))
  }

}

