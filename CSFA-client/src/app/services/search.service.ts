import { Injectable } from '@angular/core';
import { Review } from '../models/review';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable, Subject, lastValueFrom } from 'rxjs';
import { environment } from '../environments/environment';
import { Comment } from '../models/comment';

@Injectable({
  providedIn: 'root'
})
export class SearchService {


  constructor(private httpClient: HttpClient) { }

  getMovie(name: string) :Observable<Review[]>{
    const params = new HttpParams()
    .set("name", name);

    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

    
    return this.httpClient.get<Review[]>(environment.baseUrl+"/api/search", {params:params , headers: headers})
  } 

  saveToMongo(comment:Comment): Promise<any>{
    console.log(comment)
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return lastValueFrom(this.httpClient.post<Comment>(environment.baseUrl+"/api/comment" ,JSON.stringify(comment), {headers: headers}));
  }


}
