import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Quiz} from "../quiz/quiz";
import {Answer} from "../quiz/answer";
import {Question} from "../quiz/question";

@Injectable()
export class QuizService {
  timer;
  seconds: number;
  quizProgress: number;
  questionList: Question[];

  private BASE_URL = `http://localhost:8080`;
  private QUIZ_URL = `${this.BASE_URL}/quiz`;



  constructor(private http: HttpClient) {
  }
  displayTimeElapsed() {
    return Math.floor(this.seconds / 3600) + ':' + Math.floor(this.seconds / 60) + ':' + Math.floor(this.seconds % 60);
  }
    getQuiz(): Observable<Quiz> {
      return this.http.get<Quiz>(`${this.QUIZ_URL}` + `/start`);
    }

    submitScore(score: Object) {
    return this.http.post(`${this.QUIZ_URL}` + `/kk`, score);
    }

    getCorrectAnswers(answerIdList: string[]): Observable<any> {
    return this.http.post(`${this.QUIZ_URL}` + '/correctAnswers', answerIdList);
    }
}
