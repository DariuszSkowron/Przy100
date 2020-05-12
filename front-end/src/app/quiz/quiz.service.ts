import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Quiz} from "./quiz";

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  private BASE_URL = `http://localhost:8080`;
  private QUIZ_URL = `${this.BASE_URL}/quiz`;

  constructor(private http: HttpClient) {
  }
    getQuiz(): Observable<Quiz> {
      return this.http.get<Quiz>(`${this.QUIZ_URL}` + `/start`);
    }
}
